<template>
  <div id="app">

    <!-- 头部区域 -->
    <div style="height = 60px;line-height:60px;background-color: white margin-bottom:2px">
    <img src="@/assets/logo.png" alt="" style="width: 40px; position:relative; top:10px; left:20px">
    <span style="margin-left:25px;font-size:20px;">图书管理系统</span>
    </div>
  
    <!-- 侧边栏和主体 -->
    <div style="display: flex">

      
      <!-- 侧边栏导航 -->
      <div style="width: 200px; overflow: hidden; margin-right:2px; background-color: white">
        <el-menu :default-active="$router.path" router class="el-menu-demo">
          <el-menu-item index="/">首页</el-menu-item>
          
          <el-submenu index=""> 
              <template slot="title">
                <span>
                  会员管理
                </span>
              </template>
            <el-menu-item index="/addUser">  会员添加</el-menu-item>
            <el-menu-item index="/user">  会员列表 </el-menu-item>
          </el-submenu>

          
        </el-menu>
      </div>
      <!-- 主体区域 -->
      <div style="flex: 1; padding: 10px">
          <router-view/>
      </div>
    </div>
    
  </div>
</template>

