<script setup>
import { ref } from 'vue'
import { loginApi } from '@/api/login'
import { ElMessage } from 'element-plus'
import { useRouter } from 'vue-router'

let loginForm = ref({ username: '', password: '' })
let router = useRouter()

// 登录
const login = async () => {
  const result = await loginApi(loginForm.value)
  if (result.code) {
    ElMessage.success('登录成功')
    localStorage.setItem('loginUser', JSON.stringify(result.data))
    router.push('/')
  } else {
    ElMessage.error(result.msg)
  }
}

// 重置
const cancel = () => {
  loginForm.value = {
    username: '',
    password: ''
  }
}
</script>

<template>
  <div id="container">
    <div class="brand">
      <div class="logo">CF</div>
      <h1>CampusFlow</h1>
      <p class="subtitle">教务管理平台</p>
      <p class="desc">部门、员工、班级、学生与教学数据的一体化管理</p>
      <ul class="features">
        <li>统一账号与角色权限</li>
        <li>教学数据可视化报表</li>
        <li>完整的操作审计日志</li>
      </ul>
    </div>

    <div class="login-form">
      <p class="brand-line">CampusFlow 教务管理平台</p>
      <h2>账号登录</h2>
      <p class="tip">请输入教务系统账号</p>
      <el-form label-position="top">
        <el-form-item label="用户名">
          <el-input v-model="loginForm.username" placeholder="请输入用户名" size="large" />
        </el-form-item>
        <el-form-item label="密码">
          <el-input
            type="password"
            v-model="loginForm.password"
            placeholder="请输入密码"
            size="large"
            show-password
            @keyup.enter="login"
          />
        </el-form-item>
        <el-form-item>
          <el-button class="button" type="primary" size="large" @click="login">登 录</el-button>
          <el-button class="button" size="large" @click="cancel">重 置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<style scoped>
#container {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 90px;
  padding: 40px;
  box-sizing: border-box;
  background: linear-gradient(135deg, #0f172a 0%, #1e3a8a 55%, #2563eb 100%);
}

.brand {
  max-width: 420px;
  color: #ffffff;
}

.logo {
  width: 52px;
  height: 52px;
  border-radius: 14px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 20px;
  font-weight: 700;
  letter-spacing: 1px;
  background: linear-gradient(135deg, #60a5fa, #22c55e);
  margin-bottom: 24px;
}

.brand h1 {
  margin: 0;
  font-size: 42px;
  letter-spacing: 1px;
}

.subtitle {
  margin: 10px 0 18px;
  font-size: 20px;
  color: #bfdbfe;
}

.desc {
  margin: 0 0 28px;
  line-height: 1.8;
  color: #cbd5e1;
}

.features {
  margin: 0;
  padding-left: 20px;
  line-height: 2;
  color: #cbd5e1;
}

.login-form {
  width: 380px;
  padding: 36px 32px 24px;
  border-radius: 16px;
  background-color: #ffffff;
  box-shadow: 0 24px 60px rgba(2, 6, 23, 0.35);
}

.brand-line {
  margin: 0 0 10px;
  font-size: 13px;
  font-weight: 600;
  letter-spacing: 1px;
  color: #2563eb;
}

.login-form h2 {
  margin: 0;
  font-size: 24px;
  color: #0f172a;
}

.tip {
  margin: 8px 0 24px;
  font-size: 13px;
  color: #64748b;
}

.button {
  width: 100%;
  margin: 8px 0 0;
}

@media (max-width: 900px) {
  #container {
    gap: 40px;
    padding: 24px;
  }
  .brand {
    display: none;
  }
  .login-form {
    width: 100%;
    max-width: 380px;
  }
}
</style>
