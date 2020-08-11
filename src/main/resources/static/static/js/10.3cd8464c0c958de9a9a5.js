webpackJsonp([10],{EepJ:function(e,t,s){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a=s("aA9S"),i=s.n(a),n=s("hcrA"),o=s.n(n),l=s("W7zn"),r=s("cosN"),c={name:"datamanage",components:{"nav-header":l.a,"container-top":r.a},data:function(){return{username:Cookies.get("fedname"),token:Cookies.get("fedtoken"),tabList:[],activeClass:"",tableData:[],pagesize:10,currentPage:1,dialogVisibleDel:!1,formDel:{}}},mounted:function(){this.findDimensions(),this.getSceneList()},methods:{getDataList:function(){var e=this;e.axios.post("/datasetReq",e.qs.stringify({scene:e.activeClass,operator:e.username,token:e.token}),{headers:{"Content-Type":"application/x-www-form-urlencoded"}},{emulateJSON:!0}).then(function(t){t.data.message.state?e.tableData=t.data.datasets:e.$message.error(t.data.message)},function(t){e.$message.error(t.response.data.message),401==t.response.status&&(e.$router.push("/"),Cookies.set("fedname",1,{expires:-1}),Cookies.set("fedrole",1,{expires:-1}))})},handleAdd:function(){this.$router.push("/dataWork?sceneName="+this.activeClass),Cookies.set("isRead",0)},handleDelete:function(e,t){this.dialogVisibleDel=!0,this.formDel=i()({},t)},deleteThis:function(){var e=this;e.dialogVisibleDel=!1,e.axios.post("/datasetDelete",e.qs.stringify({scene:e.activeClass,datasetName:e.formDel.datasetName,operator:e.username,token:e.token}),{headers:{"Content-Type":"application/x-www-form-urlencoded"}},{emulateJSON:!0}).then(function(t){t.data.message.state?(e.$message({message:"删除数据集成功",type:"success"}),e.currentPage=1,e.getDataList()):e.$message.error(t.data.message)},function(t){e.$message.error(t.response.data.message),401==t.response.status&&(e.$router.push("/"),Cookies.set("fedname",1,{expires:-1}),Cookies.set("fedrole",1,{expires:-1}))})},handleEdit:function(e,t){this.$router.push("/dataWork?sceneName="+this.activeClass+"&datasetName="+t.datasetName),Cookies.set("isRead",1)},getSceneList:function(){var e=this;e.axios.post("/sceneReq",e.qs.stringify({specific:!1,operator:e.username,token:e.token}),{headers:{"Content-Type":"application/x-www-form-urlencoded"}},{emulateJSON:!0}).then(function(t){t.data.message.state?t.data.scenes.length?(e.tabList=t.data.scenes,e.activeClass=t.data.scenes[0].sceneName,e.getDataList()):(e.tabList=[],e.activeClass=""):e.$message.error(t.data.message)},function(t){e.$message.error(t.response.data.message),401==t.response.status&&(e.$router.push("/"),Cookies.set("fedname",1,{expires:-1}),Cookies.set("fedrole",1,{expires:-1}))})},changeBtn:function(e){this.activeClass=e,this.getDataList()},handleSizeChange:function(e){this.pagesize=e},handleCurrentChange:function(e){this.currentPage=e},findDimensions:function(){window.innerWidth?this.winWidth=window.innerWidth:document.body&&document.body.clientWidth&&(this.winWidth=document.body.clientWidth),window.innerHeight?this.winHeight=window.innerHeight:document.body&&document.body.clientHeight&&(this.winHeight=document.body.clientHeight),document.documentElement&&document.documentElement.clientHeight&&document.documentElement.clientWidth&&(this.winHeight=document.documentElement.clientHeight,this.winWidth=document.documentElement.clientWidth),o()(".account-list").attr("style","min-height:"+(this.winHeight-420)+"px;")}}},d={render:function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("el-container",[s("el-header",[s("container-top")],1),e._v(" "),s("el-container",[s("nav-header"),e._v(" "),s("el-main",[s("el-container",{staticClass:"container-main-div"},[s("div",{staticClass:"container-div"},[s("div",{staticClass:"container-main"},[s("div",{staticClass:"top-main"},[s("div",{staticClass:"page-top-line"},[s("div",{staticClass:"page-title"},[e._v("数据管理")])]),e._v(" "),s("div",{staticClass:"subbtn"},e._l(e.tabList,function(t,a){return s("div",{class:e.activeClass==e.tabList[a].sceneName?"active":"",on:{click:function(t){return e.changeBtn(e.tabList[a].sceneName)}}},[s("el-badge",{staticClass:"item",attrs:{value:e.tabList[a].institution}},[s("p",[e._v(e._s(e.tabList[a].sceneName))])])],1)}),0),e._v(" "),s("div",{staticClass:"screen-block"},[e.tabList.length>0?s("el-button",{staticClass:"yellow-btn-style",attrs:{icon:"el-icon-plus"},on:{click:e.handleAdd}},[e._v("新增数据集")]):e._e()],1)]),e._v(" "),s("div",{staticClass:"out-div"},[s("div",{staticClass:"account-list"},[[s("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData.slice((e.currentPage-1)*e.pagesize,e.currentPage*e.pagesize)}},[s("el-table-column",{attrs:{prop:"datasetName",label:"数据集名称"}}),e._v(" "),s("el-table-column",{attrs:{prop:"alignedNum",label:"对齐样本量"}}),e._v(" "),s("el-table-column",{attrs:{prop:"providers",label:"数据提供方"},scopedSlots:e._u([{key:"default",fn:function(t){return[s("div",e._l(t.row.providers,function(t,a){return s("p",[e._v(e._s(t.provider))])}),0)]}}])}),e._v(" "),s("el-table-column",{attrs:{prop:"timestamp",label:"建立时间"}}),e._v(" "),s("el-table-column",{attrs:{label:"操作",width:"240px"},scopedSlots:e._u([{key:"default",fn:function(t){return[s("div",{staticClass:"cell"},[s("div",{staticClass:"btns del-btn",on:{click:function(s){return e.handleEdit(t.$index,t.row)}}},[s("img",{attrs:{src:"static/images/look.png",alt:""}}),e._v(" "),s("p",[e._v("查看")])]),e._v(" "),s("div",{staticClass:"btns del-btn",on:{click:function(s){return e.handleDelete(t.$index,t.row)}}},[s("img",{attrs:{src:"static/images/del.png",alt:""}}),e._v(" "),s("p",[e._v("删除")])])])]}}])})],1),e._v(" "),s("el-pagination",{staticStyle:{"margin-top":"15px"},attrs:{"current-page":e.currentPage,"page-sizes":[5,10,20,50],"page-size":e.pagesize,layout:"total, sizes, prev, pager, next, jumper",total:e.tableData.length},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}})]],2)])])])]),e._v(" "),s("el-dialog",{attrs:{title:"",visible:e.dialogVisibleDel,width:"400px","lock-scroll":!1,"close-on-click-modal":!1,"close-on-press-escape":!1,"show-close":!1},on:{"update:visible":function(t){e.dialogVisibleDel=t}}},[s("img",{staticClass:"del-tip-style",attrs:{src:"static/images/del_tip.png",alt:""}}),e._v(" "),s("p",{staticClass:"del-tip-p"},[e._v("确定删除？")]),e._v(" "),s("el-form",{ref:"formDel",attrs:{model:e.formDel,"label-width":"80px"}},[s("el-form-item",{staticStyle:{display:"none"},attrs:{label:"ID"}},[s("el-input",{model:{value:e.formDel.datasetName,callback:function(t){e.$set(e.formDel,"datasetName",t)},expression:"formDel.datasetName"}})],1)],1),e._v(" "),s("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[s("el-button",{staticClass:"submit-btn-style",attrs:{type:"primary"},on:{click:e.deleteThis}},[e._v("确 定")]),e._v(" "),s("el-button",{staticClass:"cancle-btn-style",on:{click:function(t){e.dialogVisibleDel=!1}}},[e._v("取 消")])],1)],1)],1)],1)],1)},staticRenderFns:[]};var u=s("C7Lr")(c,d,!1,function(e){s("eGw8")},null,null);t.default=u.exports},eGw8:function(e,t){}});
//# sourceMappingURL=10.3cd8464c0c958de9a9a5.js.map