<template>
  <div>
    <el-form ref="loginForm" v-bind:model="form" v-bind:rules="rules" label-width="100px" class="login">
      <h2 class="login-title">欢迎登录</h2>
      <el-form-item prop="username" label="用户名">
        <el-input type="text" placeholder="请输入用户名" v-model="form.username" auto-complete="on"></el-input>
      </el-form-item>
      <el-form-item prop="password" label="密码">
        <el-input type="password" placeholder="请输入密码" v-model="form.password" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" v-on:click="submitForm('loginForm')">登录</el-button>
      </el-form-item>
    </el-form>
    <el-dialog title="温馨提示" v-bind:visible.sync="dialogVisible" width="30%" v-bind:before-close="handleClose">
      <span>请输入用户名和密码</span>
      <span slot="footer" class="dialog-footer">
      <el-button type="primary" v-on:click="dialogVisible = false">确定</el-button>
    </span>
    </el-dialog>
  </div>
</template>

<script>
    export default {
        name: 'Login',
        data() {
            return {
                form: {
                    username: '',
                    password: ''
                },
                rules: {
                    username: [
                        /*trigger:'blur' 失去焦点事件 当失去焦点时（光标不显示的时候），触发此提示*/
                        {required: true, message: '请输入用户名', trigger: 'blur'}
                    ],
                    password: [
                        /*trigger:'blur' 失去焦点事件 当失去焦点时（光标不显示的时候），触发此提示*/
                        {required: true, message: '请输入密码', trigger: 'blur'}
                    ]
                },

                // 对话框显示和隐藏
                dialogVisible: false
            }
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        //alert('submit!');
                        // 使用 vue-router 路由到指定页面，该方式称之为编程式导航
                        this.$router.push('/main')
                    } else {
                        this.dialogVisible = true;
                        return false;
                    }
                });
            },
        }
    }
</script>

<style scoped>
  .login {
    border: 1px solid #DCDFE6;
    width: 400px;
    margin: 250px auto;
    padding: 40px 35px 15px 10px;
    border-radius: 5px;
    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
    box-shadow: 0 0 25px #909399;
  }

  .login-title {
    text-align: center;
    margin: 0 auto 40px auto;
    color: #303133;
  }

</style>
