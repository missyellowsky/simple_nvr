export default {
  getCameras: '/device/getCameras', // 摄像机列表
  error: '/topic/5433d5e4e737cbe96dcef312', // url错误
  backEnd: '/topic/5433d5e4e737cbe96dcef31', // 参数错误
  getHostMapping: 'device/getHostMapping', //查询流媒体服务器
  editHostMapping: 'device/editHostMapping', //修改流媒体服务器
  setHostMapping: 'device/setHostMapping',  //设置流媒体服务器
  deleteHostMapping: 'device/deleteHostMapping', //删除流媒体服务器
  getCameraServer:'device/getCameraServer',//查询摄像机
  editCameraServer:'device/editCameraServer',//修改摄像机播放服务器
  getLiveUrl:'media/live',//直播
  queryRecordList:'device/queryRecordList',//查询录像文件(全)
  getRecordUrl:'media/record',//回看
}
