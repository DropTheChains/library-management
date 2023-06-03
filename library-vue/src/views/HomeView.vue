<template>
  <div>
    <!-- 搜索表单 -->
    <div>
      <el-input style="width: 240px" placeholder="请输入名称"></el-input>
      <el-input
        style="width: 240px; margin-left: 5px"
        placeholder="请输入联系方式"
      ></el-input>
      <el-button style="margin-left: 5px" type="primary"
        ><i class="el-icon-search"></i>搜索</el-button
      >
    </div>
    <!-- 表单主体 -->
    <el-table :data="tableData">
      <el-table-column prop="name" label="名称"></el-table-column>
      <el-table-column prop="age" label="年龄"></el-table-column>
      <el-table-column prop="address" label="地址"></el-table-column>
      <el-table-column prop="phone" label="联系方式"></el-table-column>
      <el-table-column prop="sex" label="性别"></el-table-column>
    </el-table>

    <!-- 分页 -->
    <div>
      <el-pagination
        style="margin-top: 10px"
        background
        small
        layout="prev, pager, next"
        :total="tableData.length"
      >
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  name: "HomeView",
  data() {
    return {
      tableData: [],
    };
  },
  created() {
    this.load();
  },
  methods: {
    load() {
      fetch("http://localhost:9090/user/list")
        .then((res) => res.json())
        .then((res) => {
          console.log(res);
          this.tableData = res;
        });
    },
  },
};
</script>
