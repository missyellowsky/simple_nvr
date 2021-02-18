<template>
  <div>
    <div class="Table-table" style="border: green">
      <el-table
        class="backg"
        id="table"
        :data="tableform"
        ref="tableform"
        height="720"
        :header-cell-style="tableHeaderColor"
        :row-style="tableHeaderColor"
        :cell-style = "tableHeaderColor"
      >
        <el-table-column
          align="center"
          prop="diableEdit"
          label="集群是否可编辑"
          min-width="20"
          sortable
          v-if="false"
        >
        </el-table-column>
        <el-table-column
          align="center"
          type="index"
          width="50"
        >
        </el-table-column>
        <el-table-column
          align="center"
          prop="playHost"
          label="keepLive服务器"
          min-width="20"
          sortable
        >
        </el-table-column>
        <el-table-column
          prop="host"
          label="服务器集群"
          min-width="70"
          align="center"
          sortable
          :formatter="hostFormatter"
        >
          <template slot-scope="scope">
            <el-input size="small" v-model="scope.row.host" @change="handleEdit(scope.$index, scope)" :disabled="scope.row.diableEdit"></el-input>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          prop="opp"
          label="操作"
          min-width="20"
        >
          <template slot="header">
            <el-button-group>
              <el-tooltip :disabled="false" placement="bottom" effect="light">新增</el-tooltip>
              <el-button size="mini" type="info" icon="el-icon-circle-plus" @click="addServer" plain>ADD</el-button>
            </el-button-group>
          </template>
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleEdit(scope.$index, scope.row)"
              :disabled="!scope.row.diableEdit"
            >编辑</el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleSave(scope.$index,scope.row)"
              :disabled="scope.row.diableEdit"
            >保存</el-button>
            <el-button
              size="mini"
              @click="handleDelete(scope.$index, scope.row)"
              :disabled="!scope.row.diableEdit"
            >删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <el-dialog :title="title" :visible.sync="dialogAddgsVisible"  @close="closeDialogAddgsVisible">
        <el-form  :model="addForm" :rules="rules" ref="addForm" >
          <el-form-item label="keepLive服务器" :label-width="formLabelWidth" prop="playHost">
            <el-input v-model="addForm.playHost" autocomplete="off" ></el-input>
          </el-form-item>
          <el-form-item label="服务器集群" :label-width="formLabelWidth" prop="host">
            <el-input v-model="addForm.host" ></el-input>
          </el-form-item>
        </el-form>

        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogAddgsVisible = false">取 消</el-button>
          <el-button type="primary" @click="saveAddForm('addForm')">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
  import http from '../request/http'
  import api from '../request/api'
  export default {
    name: 'ServerList',
    data() {
      var checkMultiIp = (rule,value,callback)=>{
        debugger
        let host = value.split(",");
        if(host == null || host.length == 0){
          callback(new Error("错误的ip地址: " + value));

        }
        for (let i = 0 ; i < host.length ; i++){
          if(!this.isValidIP(host[i])){
            callback(new Error("错误的ip地址: " + host[i]));
          }
        }
        callback();
      }
      var checkIp = (rule,value,callback)=>{
        debugger
        let host = value;
        if(host == null || host.length == 0 || !this.isValidIP(host)){
          callback(new Error("错误的ip地址: " + value));
        }
        callback();
      }
      return {
        title:null,
        checked: true,
        tableform: [],
        formLabelWidth: '20%',
        addLoading: false,
        addForm: [],
        radio: 1,
        //日期
        pickerOptions: {
          disabledDate(time) {
            return time.getTime() > Date.now();
          }
        },
        //搜索
        searchData: '',
        disableHost: true,
        dialogAddgsVisible: false,
        rules: {
          playHost:[
            {required: true, message:"请输入keepLive服务器地址", trigger: 'blur'},
            {validator: checkIp, trigger: 'blur' }
            ],
          host: [
            {required: true,  message:"请输入服务器地址", trigger: 'blur'},
            {validator: checkMultiIp, trigger: 'blur' }
          ]
        },
        addForm: {
          playHost: '',
          host: ''
        }
      }
    },
    methods: {
      // 修改table header的背景色
      tableHeaderColor({ row, column, rowIndex, columnIndex }) {
          return 'background: rgba(0,0,0,0.8); color: #fff; '

      },
      refreshTable(){
        http.get(api.getHostMapping, {}).then(res => {
          if(res.data != null && res.data !=undefined && res.data.code == "1"){
             let data = res.data.obj;
            for (let i = 0 ; i<data.length ; i++){
              data[i].host = data[i].host.toString();
              data[i].diableEdit = true
            }
            this.tableform = data;
          }
        })
      },
      hostFormatter(row, column){
        debugger
        let host = row.host;

        if(host){
          return host.toString();
        }
        return "";
      },
      handleDelete(index, row){
        this.$confirm('是否确认继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(_ =>{
          let params = {
            playHost: row.playHost
          }
          http.post(api.deleteHostMapping, params).then(res => {
            if(res.data != null && res.data !=undefined && res.data.code == "1"){
              this.refreshTable()
            }else{
              this.$message.error("系统错误: " + res.data.msg);
            }
          })
        }).catch(err => err)

      },
      handleEdit(index, row){
        this.changeEditState(row);
      },
      handleSave(index, row){
        let host = row.host.split(",");
        if(host == null || host.length == 0){
          this.$message.error("错误的ip地址: " + row.host);
          return;
        }
        for (let i = 0 ; i < host.length ; i++){
           if(!this.isValidIP(host[i])){
             this.$message.error("错误的ip地址: " + host[i]);
             return;
           }
        }
        let params = {
          playHost: row.playHost,
          host: host
        }
        http.post(api.editHostMapping, params).then(res => {
          if(res.data != null && res.data !=undefined && res.data.code == "1"){
            this.refreshTable()
          }else{
            this.$message.error("系统错误: " + res.data.msg);
          }
        })
        this.changeEditState(row);
      },
      changeEditState(row){
        if(row.diableEdit){
          row.diableEdit = false
        }else{
          row.diableEdit = true
        }
      },
      isValidIP(ip) {
        var reg = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/
        return reg.test(ip);
      },
      addServer(){
        this.dialogAddgsVisible = true;
        this.title="新增服务器"
      },
      saveAddForm(addForm){
        debugger
        this.$refs[addForm].validate( (valid) => {
          let params={
            playHost:this.addForm.playHost,
            host:this.addForm.host.split(',')
          }
          http.post(api.setHostMapping, params).then(res => {
            if(res.data != null && res.data !=undefined && res.data.code == "1"){
              this.dialogAddgsVisible = false;
              this.refreshTable()
            }else{
              this.$message.error("系统错误: " + res.data.msg);
            }
          })
        })
      },
      /**
       *关闭或取消模态框，清空值
       * @param
       */
      closeDialogAddgsVisible(){
        this.$refs.addForm.resetFields();//element封装的方法,清空模态框的值
        this.title="" //初始化title的值
        this.addForm={//初始化addForm中的值
          name:"",
          sort:"",
        }
      },

    },
    mounted () {
      this.refreshTable();
    },


  }
</script>

<style lang="less" scoped>

  .el-table::before{
    background-color:black;
  }

</style>
