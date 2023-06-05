<template>
  <div>
    <!-- 搜索表单 -->
    <div>
      <el-input style="width: 240px" placeholder="请输入名称" v-model="params.name"></el-input>
      <el-input
        style="width: 240px; margin-left: 5px"
        placeholder="请输入联系方式"
        v-model="params.phone"
      ></el-input>
      <el-button style="margin-left: 5px" type="primary"
        @click="load"><i class="el-icon-search"></i>搜索</el-button
      >

      <el-button style="margin-left: 5px" type=""
        @click="reset">重置</el-button
      >
    </div>
    <!-- 表单主体 -->
    <el-table :data="tableData">
      <el-table-column prop="id" label="编号"></el-table-column>
      <el-table-column prop="name" label="名称"></el-table-column>
      <el-table-column prop="username" label="用户名"></el-table-column>
      <el-table-column prop="cardid" label="卡号"></el-table-column>
      <el-table-column prop="age" label="年龄"></el-table-column>
      <el-table-column prop="address" label="地址"></el-table-column>
      <el-table-column prop="phone" label="联系方式"></el-table-column>
      <el-table-column prop="sex" label="性别"></el-table-column>

    <el-table-column label="操作">
      <template v-slot="scope">
          <el-button type="primary" @click="$router.push('/editUser?id=' + scope.row.id)"> 编辑</el-button>
            <el-popconfirm 
            style="margin-left: 5px"
            title="这是一段内容确定删除吗？" 
            @confirm="del(scope.row.id)">
              <el-button slot="reference">删除</el-button>
            </el-popconfirm>
      </template> 
    </el-table-column>
    </el-table>

    <!-- 分页 -->
    <div>    
      <el-pagination
        style="margin-top: 10px"
        background
        small
        layout="prev, pager, next"
        :current-page="params.pageNum"
        :page-size="params.pageSize"
        :total="total"
        @current-change="handleCurrentChange"
      >
      </el-pagination>
    </div>
  </div>
</template>

<script>
import request from "@/common/request";
export default {
  name: "User",
  data() {
    return {
      tableData: [],
      total: 0,
      params: {
        pageNum: 1,
        pageSize: 10  , 
        name: '',
        phone: '',
      },
    };
  },
  created() {
    this.load();
  },
  methods: {
    load() {
      request.get("/user/page",{
          params: this.params
        }).then(res => {
        if (res.code === "200") {
          this.tableData = res.data.list;
          this.total = res.data.total;
        }
      });
    },

    handleCurrentChange(pageNum){
      console.log(pageNum)
      this.params.pageNum = pageNum
      this.load()
    },
    reset(){
      this.params.name = '',
      this.params.phone = '',
      this.load()
    },
    del(id){
      request.get('/user/delete/' + id).then( res =>{
        if(res.code === "200"){
          this.$notify("删除成功！")
          this.load()
        }else{
          this.$notify(res.msg)
        }
      })
    }

    
  },
};
</script>