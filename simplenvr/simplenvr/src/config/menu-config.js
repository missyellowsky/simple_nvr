module.exports = [{
  name: '视频播放',
  id: 'videoPlay',
  icon: 'th-large',
  sub: [{
    name: '视频直播',
    componentName: 'LivePlayerLive'
  }, {
    name: '视频回看',
    componentName: 'LivePlayerRecord'
  }]
}, {
  name: 'IPC设置',
  id: 'videoconfig',
  icon: 'th-large',
  sub: [{
    name: 'IPC列表',
    componentName: 'IPCList'
  },{
    name: '服务器列表',
    componentName: 'ServerList'
  }]
},{
  name: '转码设置',
  id: 'transcodeconfig',
  icon: 'th-large',
  sub: [{
    name: '转码任务列表',
    componentName: 'TranscodeTask'
  }]
}, {
  name: 'GIS测试',
  id: 'gis',
  icon: 'th-large',
  sub: [{
    name: 'gis地图测试',
    componentName: 'Gis'
  }]
}]
