<template>
  <div>
    <div class="Table-table">
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
          type="index"
          width="20"
        >
        </el-table-column>
        <el-table-column
          align="center"
          prop="ip"
          label="摄像机IP"
          min-width="20"
          sortable
        >
        </el-table-column>
        <el-table-column
          align="center"
          prop="manufacturer"
          label="制造商"
          min-width="20"
          sortable
        >
        </el-table-column>
        <el-table-column
          align="center"
          prop="model"
          label="设备型号"
          min-width="25"
          sortable
        >
        </el-table-column>
        <el-table-column
          align="center"
          prop="firmwareVersion"
          label="主控版本"
          min-width="25"
          sortable
        >
        </el-table-column>
        <el-table-column
          align="center"
          prop="serialNumber"
          label="设备序列号"
          min-width="40"
          sortable
          show-overflow-tooltip
        >
        </el-table-column>
        <el-table-column
          align="center"
          prop="playHost"
          label="keepLive服务器"
          min-width="50"
          sortable>
          <template slot-scope="scope">
            <el-select v-model="scope.row.playHost" placeholder="请选择" :disabled="scope.row.diableEdit">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.label">
              </el-option>
            </el-select>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          prop="opp"
          label="操作"
          min-width="30"
        >
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
            </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
  import http from '../request/http'
  import api from '../request/api'
  export default {
    name: 'CameraHostConfig',
    data() {
      return {
        checked: true,
        tableform: [],
        formLabelWidth: '120px',
        editFormVisible: false,//是否显示编辑窗口
        editForm: [],
        addFormVisible: false,//是否显示新增窗口
        addLoading: false,
        addForm: [],
        radio: 1,
        options:[],
        //搜索
        searchData: '',
      }
    },
    methods: {
      // 修改table header的背景色
      tableHeaderColor({ row, column, rowIndex, columnIndex }) {
        return 'background: rgba(0,0,0,0.8); color: #fff; '
      },
      refreshTable(){
        http.get(api.getCameraServer, {}).then(res => {
          if(res.data != null && res.data !=undefined && res.data.code == "1"){
            let data = res.data.obj;
            for (let i = 0 ; i<data.length ; i++){
              data[i].diableEdit = true
            }
            this.tableform = data;
          }
        })
      },
      queryHosts(){
        http.get(api.getHostMapping, {}).then(res => {
          if(res.data != null && res.data !=undefined && res.data.code == "1"){
            let data = res.data.obj;
            let op = []
            for (let i = 0 ; i<data.length ; i++){
              op.push({
                value:data[i].playHost,
                label:data[i].playHost
              })
            }
            this.options = op;
            debugger
          }
        })
      },

      handleEdit(index, row){
        this.changeEditState(row);
      },
      handleSave(index, row){
        let params = {
          playHost: row.playHost,
          ip: row.ip
        }
        http.post(api.editCameraServer, params).then(res => {
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
    },
    mounted () {
      this.queryHosts();
      this.refreshTable();
    },
  }
</script>

<style scoped>
  .el-table::before{
    background-color:black;
  }
</style>
