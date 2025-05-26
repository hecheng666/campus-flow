<script setup>
import { computed } from 'vue'

const today = computed(() =>
  new Date().toLocaleDateString('zh-CN', { year: 'numeric', month: 'long', day: 'numeric' })
)

const modules = [
  { path: '/dept', icon: '部', title: '部门管理', desc: '维护学院与职能部门信息' },
  { path: '/emp', icon: '员', title: '员工管理', desc: '员工档案、职位与工作经历' },
  { path: '/clazz', icon: '班', title: '班级管理', desc: '班级、专业与开结课时间' },
  { path: '/stu', icon: '生', title: '学生管理', desc: '学生档案与违纪记录' },
  { path: '/report/emp', icon: '统', title: '员工统计', desc: '职位与性别分布可视化' },
  { path: '/log', icon: '志', title: '操作日志', desc: '关键操作审计与追溯' }
]

const tags = ['Spring Boot 3', 'MyBatis', 'MySQL 8', 'Vue 3', 'Element Plus', 'ECharts', 'JWT', 'Docker']
</script>

<template>
  <div class="dashboard">
    <section class="hero">
      <div>
        <p class="eyebrow">CAMPUSFLOW</p>
        <h1>教务管理平台</h1>
        <p class="desc">部门、员工、班级、学生与教学数据的一体化管理</p>
      </div>
      <div class="date">{{ today }}</div>
    </section>

    <section class="cards">
      <router-link v-for="item in modules" :key="item.path" :to="item.path" class="card">
        <span class="icon">{{ item.icon }}</span>
        <h3>{{ item.title }}</h3>
        <p>{{ item.desc }}</p>
      </router-link>
    </section>

    <section class="stack">
      <h3>技术栈</h3>
      <div class="tags">
        <span v-for="tag in tags" :key="tag">{{ tag }}</span>
      </div>
    </section>
  </div>
</template>

<style scoped>
.dashboard {
  padding: 8px 4px 24px;
}

.hero {
  display: flex;
  align-items: flex-end;
  justify-content: space-between;
  padding: 36px 40px;
  border-radius: 16px;
  color: #ffffff;
  background: linear-gradient(120deg, #0f172a 0%, #1e3a8a 60%, #2563eb 100%);
}

.eyebrow {
  margin: 0 0 8px;
  font-size: 12px;
  letter-spacing: 3px;
  color: #93c5fd;
}

.hero h1 {
  margin: 0;
  font-size: 32px;
}

.desc {
  margin: 12px 0 0;
  color: #cbd5e1;
}

.date {
  font-size: 14px;
  color: #bfdbfe;
  white-space: nowrap;
}

.cards {
  display: grid;
  grid-template-columns: repeat(3, minmax(0, 1fr));
  gap: 18px;
  margin-top: 24px;
}

.card {
  display: block;
  padding: 22px;
  border: 1px solid #e2e8f0;
  border-radius: 14px;
  background-color: #ffffff;
  color: inherit;
  text-decoration: none;
  transition: transform 0.2s, box-shadow 0.2s, border-color 0.2s;
}

.card:hover {
  transform: translateY(-3px);
  border-color: #bfdbfe;
  box-shadow: 0 12px 28px rgba(15, 23, 42, 0.1);
}

.icon {
  display: inline-flex;
  width: 38px;
  height: 38px;
  align-items: center;
  justify-content: center;
  border-radius: 10px;
  font-size: 16px;
  font-weight: 600;
  color: #1d4ed8;
  background-color: #eff6ff;
}

.card h3 {
  margin: 14px 0 6px;
  font-size: 17px;
  color: #0f172a;
}

.card p {
  margin: 0;
  font-size: 13px;
  color: #64748b;
}

.stack {
  margin-top: 24px;
  padding: 22px 24px;
  border: 1px solid #e2e8f0;
  border-radius: 14px;
  background-color: #ffffff;
}

.stack h3 {
  margin: 0 0 14px;
  font-size: 15px;
  color: #0f172a;
}

.tags {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
}

.tags span {
  padding: 6px 12px;
  border-radius: 999px;
  font-size: 12px;
  color: #1d4ed8;
  background-color: #eff6ff;
}

@media (max-width: 1100px) {
  .cards {
    grid-template-columns: repeat(2, minmax(0, 1fr));
  }
}

@media (max-width: 700px) {
  .hero {
    flex-direction: column;
    align-items: flex-start;
    gap: 12px;
  }
  .cards {
    grid-template-columns: 1fr;
  }
}
</style>
