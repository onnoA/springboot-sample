package com.onnoa.springboot.commons.utils;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.apache.commons.lang3.StringUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.ContentHandler;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Description: XML 解析工具类
 * @Author: onnoA
 * @Date: 2019/11/13 14:04
 */
public class XMLUtil {

    @SuppressWarnings("unchecked")
    public static <T> T toBean(String xmlStr, Class<T> cls) {
        XStream xstream = new XStream(new DomDriver());
        xstream.processAnnotations(cls);
        T t = (T) xstream.fromXML(xmlStr);
        return t;
    }

    /**
     * 基于JAXB的注解的Bean to xml
     *
     * @param bean
     * @return
     * @throws JAXBException
     */
    public static String bean2XmlString(Object bean) throws JAXBException {

        StringWriter writer = new StringWriter();
        bean2Xml(bean, writer);

        return writer.toString();
    }

    /**
     * 基于JAXB的注解的Bean to xml
     *
     * @param bean
     * @return
     * @throws JAXBException
     */
    public static void bean2Xml(Object bean, StringWriter writer) throws JAXBException {
        Marshaller marshaller = createMarshaller(bean);
        marshaller.marshal(bean, writer);
    }

    /**
     * 基于JAXB的注解的Bean to xml
     *
     * @param bean
     * @return
     * @throws JAXBException
     */
    public static void bean2Xml(Object bean, ContentHandler handler) throws JAXBException {
        Marshaller marshaller = createMarshaller(bean);
        marshaller.marshal(bean, handler);
    }

    /**
     * 读取XML到bean
     *
     * @param xml
     * @param clazz
     * @return
     * @throws JAXBException
     */
    public static <T> T xml2Bean(String xml, Class<T> clazz) throws JAXBException {
        try {
            return xml2Bean(xml, clazz, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 读取XML到bean
     *
     * @param xml
     * @param clazz
     * @return
     * @throws JAXBException
     * @throws UnsupportedEncodingException
     */
    public static <T> T xml2Bean(String xml, Class<T> clazz, String charsetName)
            throws JAXBException, UnsupportedEncodingException {
        InputStream is = new ByteArrayInputStream(xml.getBytes(charsetName));
        return xml2Bean(is, clazz);
    }

    /**
     * 读取XML到bean
     *
     * @param is
     * @param clazz
     * @return
     * @throws JAXBException
     */
    @SuppressWarnings("unchecked")
    public static <T> T xml2Bean(InputStream is, Class<T> clazz) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Object unmarshal = unmarshaller.unmarshal(is);

        return (T) unmarshal;
    }

    private static Marshaller createMarshaller(Object bean) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(bean.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        return marshaller;
    }

    /**
     * 功能描述: 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
     * @param strxmlParam
     * @return
     * @date 2019/11/30 12:26
     */
    @SuppressWarnings("rawtypes")
    public static Map<String, String> xml2map(String strxmlParam) throws JDOMException, IOException {
        if (StringUtils.isBlank(strxmlParam)) {
            return null;
        }
        String strxml = strxmlParam.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");

        Map<String, String> m = new HashMap<>();

        InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
        SAXBuilder builder = new SAXBuilder();
        builder.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        builder.setFeature("http://xml.org/sax/features/external-general-entities", false);
        builder.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        builder.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        builder.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        Document doc = builder.build(in);
        Element root = doc.getRootElement();
        List list = root.getChildren();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Element e = (Element) it.next();
            String k = e.getName();
            String v = "";
            List children = e.getChildren();
            if (children.isEmpty()) {
                v = e.getTextNormalize();
            } else {
                v = getChildrenText(children);
            }

            m.put(k, v);
        }

        // 关闭流
        in.close();

        return m;
    }

    /**
     * 功能描述: 获取子结点的xml
     * @param children 自己点list集合
     * @return 子节点文本
     * @date 2019/11/30 12:25
     */
    @SuppressWarnings("rawtypes")
    private static String getChildrenText(List children) {
        StringBuilder sb = new StringBuilder();
        if (!children.isEmpty()) {
            Iterator it = children.iterator();
            while (it.hasNext()) {
                Element e = (Element) it.next();
                String name = e.getName();
                String value = e.getTextNormalize();
                List list = e.getChildren();
                sb.append("<" + name + ">");
                if (!list.isEmpty()) {
                    sb.append(getChildrenText(list));
                }
                sb.append(value);
                sb.append("</" + name + ">");
            }
        }

        return sb.toString();
    }


    /**
     * 功能描述: 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
     * @param strxmlParam xml 文本
     * @return  map集合
     * @date 2019/11/30 12:25
     */
    @SuppressWarnings("rawtypes")
    public static Map<String, Object> xmlAll2map(String strxmlParam) throws JDOMException, IOException {
        if (StringUtils.isBlank(strxmlParam)) {
            return null;
        }
        String strxml = strxmlParam.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");

        Map<String, Object> m = new HashMap<>();

        InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
        SAXBuilder builder = new SAXBuilder();
        builder.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        builder.setFeature("http://xml.org/sax/features/external-general-entities", false);
        builder.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        builder.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        builder.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        Document doc = builder.build(in);
        Element root = doc.getRootElement();
        List list = root.getChildren();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Element e = (Element) it.next();
            String k = e.getName();
            List children = e.getChildren();
            if (children.isEmpty()) {
                m.put(k, e.getTextNormalize());
            } else {
                m.put(k, getChildrenMap(children));
            }
        }
        // 关闭流
        in.close();
        return m;
    }

    /**
     * 获取子结点的xml
     *
     * @param children
     * @return String
     */
    @SuppressWarnings("rawtypes")
    private static Map<String, Object> getChildrenMap(List children) {
        Map<String, Object> m = new HashMap<>();
        if (!children.isEmpty()) {
            Iterator it = children.iterator();
            while (it.hasNext()) {
                Element e = (Element) it.next();
                String name = e.getName();
                List list = e.getChildren();
                if (list.isEmpty()) {
                    m.put(name, e.getTextNormalize());
                } else {
                    m.put(name, getChildrenMap(list));
                }
            }
        }
        return m;
    }
}
