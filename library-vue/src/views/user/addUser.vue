<template>
    <div>
        <h2>新增会员页面</h2>
        
        <el-form v-model="form" :inline = "true" :rules="rules" ref="ruleForm">
            <el-form-item label="姓名">
                <el-input v-model="form.name" placeholder="请输入姓名："></el-input>
            </el-form-item>
            <el-form-item label="用户名">
                <el-input v-model="form.username" placeholder="请输入用户名："></el-input>
            </el-form-item>
            <el-form-item label="年龄">
                <el-input v-model="form.age" placeholder="请输入年龄："></el-input>
            </el-form-item>
            <el-form-item label="性别">
                <!-- <el-input v-model="form.sex" placeholder="请输入性别："></el-input> -->
                <el-radio v-model="form.sex" label="男">男</el-radio>
                <el-radio v-model="form.sex" label="女">女</el-radio>
            </el-form-item>
            <el-form-item label="联系方式">
                <el-input v-model="form.phone" placeholder="请输入联系方式："></el-input>
            </el-form-item>
            <el-form-item label="地址">
                <el-input v-model="form.address" placeholder="请输入地址："></el-input>
            </el-form-item>
        </el-form>
        <div>
        <el-button @click="save">新增</el-button>
        <el-button @click="reset">重置</el-button>
        </div>
    </div>
</template>

<script>
import request from '@/common/request'

export default ({
    name: "addUser",
    data() {
        return {
            form: {

            },
            rules: {
                name: [
                    { required: true, message: '请输入名称', trigger: 'blur' },
                { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
                ],
                username:[
                    {require:true,message:'请输入用户名',trigger :'blur'}
                ]
            }
        }
    },
    methods: {
        save() {
            // this.$refs['ruleForm'].validate((valid) => {
            //     if(valid){
                        

            //     }else{

            //     }
            // })
            request.post('/user/save',this.form).then( res =>{
                if( res.code === '200'){
                    this.$notify.success('新增成功')
                    }else{
                    this.$notify.error(res.msg)
                    }            
                })
        },   
        reset(){
            this.form = {}
        }
    },  
    
})
</script>
