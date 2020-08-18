webpackJsonp([7],{"H7+k":function(e,t){},w8KA:function(e,t,o){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var s=o("aA9S"),a=o.n(s),i=o("hcrA"),l=o.n(i),n=o("W7zn"),r=o("cosN"),d={name:"nodeManage",components:{"nav-header":n.a,"container-top":r.a},data:function(){return{username:Cookies.get("fedname"),token:Cookies.get("fedtoken"),tableData:[],pagesize:10,currentPage:1,headImg:"",dialogVisibleAdd:!1,formAdd:{},rulesAdd:{nodeName:[{required:!0,message:"请填写节点名称",trigger:"blur"}]},headImg2:"",dialogVisibleEdit:!1,form:{},rulesEdit:{nodeName:[{required:!0,message:"请填写节点名称",trigger:"blur"}]},dialogVisibleDel:!1,formDel:{}}},mounted:function(){this.findDimensions(),this.getNodeList()},methods:{getNodeList:function(){var e=this;e.axios.post(e.BASE_URL+"/nodeReq",e.qs.stringify({operator:e.username,token:e.token}),{headers:{"Content-Type":"application/x-www-form-urlencoded"}},{emulateJSON:!0}).then(function(t){t.data.message.state?e.tableData=t.data.nodes:e.$message.error(t.data.message)},function(t){e.$message.error(t.response.data.message),401==t.response.status&&(e.$router.push("/"),Cookies.set("fedname",1,{expires:-1}),Cookies.set("fedrole",1,{expires:-1}))})},handleAdd:function(){this.dialogVisibleAdd=!0},imgChange:function(){var e=this,t=document.querySelector(".add-uploadimg input[type=file]").files[0];var o=new FileReader;o.onloadend=function(){l()("#base64Img").show(),e.headImg=o.result},t&&o.readAsDataURL(t)},addAccount:function(e){var t=this;t.$refs[e].validate(function(e){if(!e)return!1;t.dialogVisibleAdd=!1,t.axios.post(t.BASE_URL+"/nodeCreate",t.qs.stringify({nodeName:t.formAdd.nodeName,ipAddress:t.formAdd.ipAddress||"",port:t.formAdd.port||"",CSV_path:t.formAdd.CSV_path||"",logo:t.headImg||"",operator:t.username,token:t.token}),{headers:{"Content-Type":"application/x-www-form-urlencoded"}},{emulateJSON:!0}).then(function(e){e.data.message.state?(t.$message({message:"创建节点成功",type:"success"}),t.currentPage=1,t.getNodeList()):t.$message.error(e.data.message)},function(e){t.$message.error(e.response.data.message),401==e.response.status&&(t.$router.push("/"),Cookies.set("fedname",1,{expires:-1}),Cookies.set("fedrole",1,{expires:-1}))})})},handleEdit:function(e,t){this.dialogVisibleEdit=!0,this.form=a()({},t),this.form.oldName=t.nodeName,this.headImg2=t.logo},imgChange2:function(){var e=this,t=document.querySelector(".edit-uploadimg input[type=file]").files[0];var o=new FileReader;o.onloadend=function(){l()("#base64Img2").show(),e.headImg2=o.result},t&&o.readAsDataURL(t)},editAccount:function(e){var t=this;t.$refs[e].validate(function(e){if(!e)return!1;t.dialogVisibleEdit=!1,t.axios.post(t.BASE_URL+"/nodeModify",t.qs.stringify({old_nodeName:t.form.oldName,nodeName:t.form.nodeName,ipAddress:t.form.ipAddress||"",port:t.form.port||"",CSV_path:t.form.CSV_path||"",logo:t.headImg2||"",operator:t.username,token:t.token}),{headers:{"Content-Type":"application/x-www-form-urlencoded"}},{emulateJSON:!0}).then(function(e){e.data.message.state?(t.$message({message:"修改成功",type:"success"}),t.currentPage=1,t.getNodeList()):t.$message.error(e.data.message)},function(e){t.$message.error(e.response.data.message),401==e.response.status&&(t.$router.push("/"),Cookies.set("fedname",1,{expires:-1}),Cookies.set("fedrole",1,{expires:-1}))})})},handleDelete:function(e,t){this.dialogVisibleDel=!0,this.formDel=a()({},t)},deleteThis:function(){var e=this;e.dialogVisibleDel=!1,e.axios.post(e.BASE_URL+"/nodeDelete",e.qs.stringify({nodeName:e.formDel.nodeName,operator:e.username,token:e.token}),{headers:{"Content-Type":"application/x-www-form-urlencoded"}},{emulateJSON:!0}).then(function(t){t.data.message.state?(e.$message({message:"删除节点成功",type:"success"}),e.currentPage=1,e.getNodeList()):e.$message.error(t.data.message)},function(t){e.$message.error(t.response.data.message),401==t.response.status&&(e.$router.push("/"),Cookies.set("fedname",1,{expires:-1}),Cookies.set("fedrole",1,{expires:-1}))})},handleSizeChange:function(e){this.pagesize=e},handleCurrentChange:function(e){this.currentPage=e},findDimensions:function(){window.innerWidth?this.winWidth=window.innerWidth:document.body&&document.body.clientWidth&&(this.winWidth=document.body.clientWidth),window.innerHeight?this.winHeight=window.innerHeight:document.body&&document.body.clientHeight&&(this.winHeight=document.body.clientHeight),document.documentElement&&document.documentElement.clientHeight&&document.documentElement.clientWidth&&(this.winHeight=document.documentElement.clientHeight,this.winWidth=document.documentElement.clientWidth),l()(".node-list").attr("style","min-height:"+(this.winHeight-360)+"px;")}}},c={render:function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("el-container",[o("el-header",[o("container-top")],1),e._v(" "),o("el-container",[o("nav-header"),e._v(" "),o("el-main",[o("el-container",{staticClass:"container-main-div"},[o("div",{staticClass:"container-div"},[o("div",{staticClass:"container-main"},[o("div",{staticClass:"top-main"},[o("div",{staticClass:"page-top-line"},[o("div",{staticClass:"page-title"},[e._v("节点管理")])]),e._v(" "),o("div",{staticClass:"screen-block"},[o("el-button",{staticClass:"create-node",attrs:{icon:"el-icon-plus"},on:{click:e.handleAdd}},[e._v("创建节点")])],1)]),e._v(" "),o("div",{staticClass:"out-div"},[o("div",{staticClass:"node-list"},[[o("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData.slice((e.currentPage-1)*e.pagesize,e.currentPage*e.pagesize)}},[o("el-table-column",{attrs:{prop:"id",label:"序号",width:"80"},scopedSlots:e._u([{key:"default",fn:function(t){return[o("span",[e._v(e._s((e.currentPage-1)*e.pagesize+t.$index+1))])]}}])}),e._v(" "),o("el-table-column",{attrs:{prop:"nodeName",label:"节点名称"}}),e._v(" "),o("el-table-column",{attrs:{prop:"logo",label:"logo"},scopedSlots:e._u([{key:"default",fn:function(e){return[o("img",{attrs:{src:e.row.logo,alt:"",height:"30px"}})]}}])}),e._v(" "),o("el-table-column",{attrs:{prop:"ipAddress",label:"IP地址"}}),e._v(" "),o("el-table-column",{attrs:{prop:"port",label:"端口号"}}),e._v(" "),o("el-table-column",{attrs:{prop:"status",label:"状态"},scopedSlots:e._u([{key:"default",fn:function(t){return[t.row.status?o("p",[e._v("接通")]):o("p",[e._v("失败")])]}}])}),e._v(" "),o("el-table-column",{attrs:{label:"操作",width:"200px"},scopedSlots:e._u([{key:"default",fn:function(t){return[o("div",{staticClass:"cell"},[o("div",{staticClass:"btns edit-btn",on:{click:function(o){return e.handleEdit(t.$index,t.row)}}},[o("img",{attrs:{src:"static/images/edit.png",alt:""}}),e._v(" "),o("p",[e._v("修改")])]),e._v(" "),o("div",{staticClass:"btns del-btn",on:{click:function(o){return e.handleDelete(t.$index,t.row)}}},[o("img",{attrs:{src:"static/images/del.png",alt:""}}),e._v(" "),o("p",[e._v("删除")])])])]}}])})],1),e._v(" "),o("el-pagination",{staticStyle:{"margin-top":"15px"},attrs:{"current-page":e.currentPage,"page-sizes":[5,10,20,50],"page-size":e.pagesize,layout:"total, sizes, prev, pager, next, jumper",total:e.tableData.length},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}})]],2)])])])]),e._v(" "),o("el-dialog",{attrs:{title:"创建节点",visible:e.dialogVisibleAdd,width:"500px","lock-scroll":!1,"close-on-click-modal":!1,"close-on-press-escape":!1,"show-close":!1},on:{"update:visible":function(t){e.dialogVisibleAdd=t}}},[o("el-form",{ref:"formAdd",attrs:{rules:e.rulesAdd,model:e.formAdd,"label-width":"120px"}},[o("el-form-item",{attrs:{label:"节点名称",prop:"nodeName"}},[o("el-input",{model:{value:e.formAdd.nodeName,callback:function(t){e.$set(e.formAdd,"nodeName",t)},expression:"formAdd.nodeName"}})],1),e._v(" "),o("el-form-item",{staticStyle:{width:"400px"},attrs:{label:"机构logo"}},[o("img",{attrs:{id:"base64Img",src:e.headImg}}),e._v(" "),o("div",{staticClass:"img-div"},[o("p",[e._v("选择图片")]),e._v(" "),o("el-input",{staticClass:"add-uploadimg",staticStyle:{opacity:"0"},attrs:{type:"file"},on:{change:e.imgChange},model:{value:e.formAdd.logo,callback:function(t){e.$set(e.formAdd,"logo",t)},expression:"formAdd.logo"}})],1)]),e._v(" "),o("el-form-item",{attrs:{label:"IP地址",prop:"ipAddress"}},[o("el-input",{model:{value:e.formAdd.ipAddress,callback:function(t){e.$set(e.formAdd,"ipAddress",t)},expression:"formAdd.ipAddress"}})],1),e._v(" "),o("el-form-item",{attrs:{label:"端口号",prop:"port"}},[o("el-input",{model:{value:e.formAdd.port,callback:function(t){e.$set(e.formAdd,"port",t)},expression:"formAdd.port"}})],1),e._v(" "),o("el-form-item",{attrs:{label:"CSV文件路径",prop:"CSV_path"}},[o("el-input",{model:{value:e.formAdd.CSV_path,callback:function(t){e.$set(e.formAdd,"CSV_path",t)},expression:"formAdd.CSV_path"}})],1)],1),e._v(" "),o("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[o("el-button",{staticClass:"submit-btn-style",attrs:{type:"primary"},on:{click:function(t){return e.addAccount("formAdd")}}},[e._v("确 定")]),e._v(" "),o("el-button",{staticClass:"cancle-btn-style",on:{click:function(t){e.dialogVisibleAdd=!1}}},[e._v("取 消")])],1)],1),e._v(" "),o("el-dialog",{attrs:{title:"修改节点",visible:e.dialogVisibleEdit,width:"500px","lock-scroll":!1,"close-on-click-modal":!1,"close-on-press-escape":!1,"show-close":!1},on:{"update:visible":function(t){e.dialogVisibleEdit=t}}},[o("el-form",{ref:"form",attrs:{rules:e.rulesEdit,model:e.form,"label-width":"120px"}},[o("el-form-item",{staticStyle:{display:"none"},attrs:{label:"节点名称",prop:"oldName"}},[o("el-input",{model:{value:e.form.oldName,callback:function(t){e.$set(e.form,"oldName",t)},expression:"form.oldName"}})],1),e._v(" "),o("el-form-item",{attrs:{label:"节点名称",prop:"nodeName"}},[o("el-input",{model:{value:e.form.nodeName,callback:function(t){e.$set(e.form,"nodeName",t)},expression:"form.nodeName"}})],1),e._v(" "),o("el-form-item",{staticStyle:{width:"400px"},attrs:{label:"机构logo"}},[o("img",{attrs:{id:"base64Img2",src:e.headImg2}}),e._v(" "),o("div",{staticClass:"img-div"},[o("p",[e._v("选择图片")]),e._v(" "),o("el-input",{staticClass:"edit-uploadimg",staticStyle:{opacity:"0"},attrs:{type:"file"},on:{change:e.imgChange2},model:{value:e.form.logo,callback:function(t){e.$set(e.form,"logo",t)},expression:"form.logo"}})],1)]),e._v(" "),o("el-form-item",{attrs:{label:"IP地址",prop:"ipAddress"}},[o("el-input",{model:{value:e.form.ipAddress,callback:function(t){e.$set(e.form,"ipAddress",t)},expression:"form.ipAddress"}})],1),e._v(" "),o("el-form-item",{attrs:{label:"端口号",prop:"port"}},[o("el-input",{model:{value:e.form.port,callback:function(t){e.$set(e.form,"port",t)},expression:"form.port"}})],1),e._v(" "),o("el-form-item",{attrs:{label:"CSV文件路径",prop:"CSV_path"}},[o("el-input",{model:{value:e.form.CSV_path,callback:function(t){e.$set(e.form,"CSV_path",t)},expression:"form.CSV_path"}})],1)],1),e._v(" "),o("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[o("el-button",{staticClass:"submit-btn-style",attrs:{type:"primary"},on:{click:function(t){return e.editAccount("form")}}},[e._v("确 定")]),e._v(" "),o("el-button",{staticClass:"cancle-btn-style",on:{click:function(t){e.dialogVisibleEdit=!1}}},[e._v("取 消")])],1)],1),e._v(" "),o("el-dialog",{attrs:{title:"",visible:e.dialogVisibleDel,width:"400px","lock-scroll":!1,"close-on-click-modal":!1,"close-on-press-escape":!1,"show-close":!1},on:{"update:visible":function(t){e.dialogVisibleDel=t}}},[o("img",{staticClass:"del-tip-style",attrs:{src:"static/images/del_tip.png",alt:""}}),e._v(" "),o("p",{staticClass:"del-tip-p"},[e._v("确定删除？")]),e._v(" "),o("el-form",{ref:"formDel",attrs:{model:e.formDel,"label-width":"80px"}},[o("el-form-item",{staticStyle:{display:"none"},attrs:{label:"ID"}},[o("el-input",{model:{value:e.formDel.nodeName,callback:function(t){e.$set(e.formDel,"nodeName",t)},expression:"formDel.nodeName"}})],1)],1),e._v(" "),o("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[o("el-button",{staticClass:"submit-btn-style",attrs:{type:"primary"},on:{click:e.deleteThis}},[e._v("确 定")]),e._v(" "),o("el-button",{staticClass:"cancle-btn-style",on:{click:function(t){e.dialogVisibleDel=!1}}},[e._v("取 消")])],1)],1)],1)],1)],1)},staticRenderFns:[]};var m=o("C7Lr")(d,c,!1,function(e){o("H7+k")},null,null);t.default=m.exports}});
//# sourceMappingURL=7.21bdd0faf04d3bd69897.js.map