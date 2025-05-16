import axios from 'axios'
import { ElMessage } from 'element-plus'
import router from '../router'

const request = axios.create({
  baseURL: '/api',
  timeout: 30000
})

// 请求拦截器：从 localStorage 取出 token 放入请求头
request.interceptors.request.use((config) => {
  const loginUser = JSON.parse(localStorage.getItem('loginUser') || 'null')
  if (loginUser?.token) {
    config.headers.token = loginUser.token
  }
  return config
})

// 响应拦截器：统一处理 401 与网络异常
request.interceptors.response.use(
  (response) => response.data,
  (error) => {
    if (error.response?.status === 401) {
      localStorage.removeItem('loginUser')
      ElMessage.error('登录已失效，请重新登录')
      router.push('/login')
    } else if (!error.response) {
      ElMessage.error('网络异常，请检查后端服务是否启动')
    }
    return Promise.reject(error)
  }
)

export default request
