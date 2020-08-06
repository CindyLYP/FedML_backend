webpackJsonp([5],{"2get":function(e,t,s){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var o=s("aA9S"),r=s.n(o),a=s("hcrA"),i=s.n(a),n=s("W7zn"),l=s("cosN"),c={name:"homepage",components:{"nav-header":n.a,"container-top":l.a},data:function(){return{username:Cookies.get("fedname"),token:Cookies.get("fedtoken"),screenSelect:"1",inputIdentity:"节点管理员",inputAccount:"",identityList:[{value:"节点管理员",label:"节点管理员"},{value:"节点普通用户",label:"节点普通用户"}],tableData:[],pagesize:10,currentPage:1,dialogVisibleAdd:!1,formAdd:{},rulesAdd:{userName:[{required:!0,message:"请填写姓名",trigger:"blur"}],userAccount:[{required:!0,message:"请填写账号",trigger:"blur"}],password:[{required:!0,message:"请填写密码",trigger:"blur"}],institution:[{required:!0,message:"请选择所属机构",trigger:"change"}],userType:[{required:!0,message:"请选择身份",trigger:"change"}]},userTypeList:[],dialogVisibleEdit:!1,form:{},rulesEdit:{userName:[{required:!0,message:"请填写姓名",trigger:"blur"}],userAccount:[{required:!0,message:"请填写账号",trigger:"blur"}],password:[{required:!0,message:"请填写密码",trigger:"blur"}],institution:[{required:!0,message:"请选择所属机构",trigger:"change"}],userType:[{required:!0,message:"请选择身份",trigger:"change"}]},dialogVisibleDel:!1,formDel:{}}},mounted:function(){this.findDimensions(),this.getAccountList(),this.getNodeList()},methods:{getAccountList:function(){var e=this;e.axios.post("/userReq",e.qs.stringify({askUserType:"",askUserAccount:"",operator:e.username,token:e.token}),{headers:{"Content-Type":"application/x-www-form-urlencoded"}},{emulateJSON:!0}).then(function(t){t.data.message.state?e.tableData=t.data.users:e.$message.error(t.data.message)},function(t){e.$message.error(t.response.data.message),401==t.response.status&&(e.$router.push("/"),Cookies.set("fedname",1,{expires:-1}),Cookies.set("fedrole",1,{expires:-1}))})},handleAdd:function(){this.dialogVisibleAdd=!0},addAccount:function(e){var t=this;t.$refs[e].validate(function(e){if(!e)return!1;t.dialogVisibleAdd=!1,t.axios.post("/userCreate",t.qs.stringify({userName:t.formAdd.userName,userAccount:t.formAdd.userAccount,password:t.formAdd.password,userType:100==t.formAdd.userType?"节点管理员":"节点普通用户",institution:t.formAdd.institution,operator:t.username,token:t.token}),{headers:{"Content-Type":"application/x-www-form-urlencoded"}},{emulateJSON:!0}).then(function(e){e.data.message.state?(t.$message({message:"创建账号成功",type:"success"}),t.currentPage=1,t.formAdd={},t.getAccountList()):t.$message.error(e.data.message)},function(e){t.$message.error(e.response.data.message),401==e.response.status&&(t.$router.push("/"),Cookies.set("fedname",1,{expires:-1}),Cookies.set("fedrole",1,{expires:-1}))})})},handleEdit:function(e,t){this.dialogVisibleEdit=!0,this.form=r()({},t),"节点管理员"==t.userType?this.form.userType=100:this.form.userType=200},editAccount:function(e){var t=this;t.$refs[e].validate(function(e){if(!e)return!1;t.dialogVisibleEdit=!1,t.axios.post("/userModify",t.qs.stringify({userName:t.form.userName,userAccount:t.form.userAccount,password:t.form.password,userType:100==t.form.userType?"节点管理员":"节点普通用户",institution:t.form.institution,operator:t.username,token:t.token}),{headers:{"Content-Type":"application/x-www-form-urlencoded"}},{emulateJSON:!0}).then(function(e){e.data.message.state?(t.$message({message:"修改成功",type:"success"}),t.currentPage=1,t.getAccountList()):t.$message.error(e.data.message)},function(e){t.$message.error(e.response.data.message),401==e.response.status&&(t.$router.push("/"),Cookies.set("fedname",1,{expires:-1}),Cookies.set("fedrole",1,{expires:-1}))})})},handleDelete:function(e,t){this.dialogVisibleDel=!0,this.formDel=r()({},t)},deleteThis:function(){var e=this;e.dialogVisibleDel=!1,e.axios.post("/userDelete",e.qs.stringify({userAccount:e.formDel.userAccount,operator:e.username,token:e.token}),{headers:{"Content-Type":"application/x-www-form-urlencoded"}},{emulateJSON:!0}).then(function(t){t.data.message.state?(e.$message({message:"删除账号成功",type:"success"}),e.currentPage=1,e.getAccountList()):e.$message.error(t.data.message)},function(t){e.$message.error(t.response.data.message),401==t.response.status&&(e.$router.push("/"),Cookies.set("fedname",1,{expires:-1}),Cookies.set("fedrole",1,{expires:-1}))})},getNodeList:function(){var e=this;e.axios.post("/nodeReq",e.qs.stringify({operator:e.username,token:e.token}),{headers:{"Content-Type":"application/x-www-form-urlencoded"}},{emulateJSON:!0}).then(function(t){t.data.message.state?e.userTypeList=t.data.nodes:e.$message.error(t.data.message)},function(t){e.$message.error(t.response.data.message),401==t.response.status&&(e.$router.push("/"),Cookies.set("fedname",1,{expires:-1}),Cookies.set("fedrole",1,{expires:-1}))})},search:function(){var e=this;1==e.screenSelect?e.axios.post("/userReq",e.qs.stringify({askUserType:e.inputIdentity,askUserAccount:"",operator:e.username,token:e.token}),{headers:{"Content-Type":"application/x-www-form-urlencoded"}},{emulateJSON:!0}).then(function(t){t.data.message.state?(e.$message({message:"搜索成功，以下为搜索结果",type:"success"}),e.tableData=t.data.users):e.$message.error(t.data.message)},function(t){e.$message.error(t.response.data.message),401==t.response.status&&(e.$router.push("/"),Cookies.set("fedname",1,{expires:-1}),Cookies.set("fedrole",1,{expires:-1}))}):2==e.screenSelect?""==e.inputAccount?this.$message.error("请输入需要搜索的账号"):e.axios.post("/userReq",e.qs.stringify({askUserType:"",askUserAccount:e.inputAccount,operator:e.username,token:e.token}),{headers:{"Content-Type":"application/x-www-form-urlencoded"}},{emulateJSON:!0}).then(function(t){t.data.message.state?(e.$message({message:"搜索成功，以下为搜索结果",type:"success"}),e.tableData=t.data.users):e.$message.error(t.data.message)},function(t){e.$message.error(t.response.data.message),401==t.response.status&&(e.$router.push("/"),Cookies.set("fedname",1,{expires:-1}),Cookies.set("fedrole",1,{expires:-1}))}):3==e.screenSelect&&e.getAccountList()},handleSizeChange:function(e){this.pagesize=e},handleCurrentChange:function(e){this.currentPage=e},findDimensions:function(){window.innerWidth?this.winWidth=window.innerWidth:document.body&&document.body.clientWidth&&(this.winWidth=document.body.clientWidth),window.innerHeight?this.winHeight=window.innerHeight:document.body&&document.body.clientHeight&&(this.winHeight=document.body.clientHeight),document.documentElement&&document.documentElement.clientHeight&&document.documentElement.clientWidth&&(this.winHeight=document.documentElement.clientHeight,this.winWidth=document.documentElement.clientWidth),i()(".account-list").attr("style","min-height:"+(this.winHeight-360)+"px;")}}},u={render:function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("el-container",[s("el-header",[s("container-top")],1),e._v(" "),s("el-container",[s("nav-header"),e._v(" "),s("el-main",[s("el-container",{staticClass:"container-main-div"},[s("div",{staticClass:"container-div"},[s("div",{staticClass:"container-main"},[s("div",{staticClass:"top-main"},[s("div",{staticClass:"page-top-line"},[s("div",{staticClass:"page-title"},[e._v("账户管理")])]),e._v(" "),s("div",{staticClass:"screen-block"},[s("div",[s("el-radio",{attrs:{label:"1"},model:{value:e.screenSelect,callback:function(t){e.screenSelect=t},expression:"screenSelect"}},[e._v("身份")]),e._v(" "),s("el-select",{staticClass:"screen-input",attrs:{placeholder:"请选择身份"},model:{value:e.inputIdentity,callback:function(t){e.inputIdentity=t},expression:"inputIdentity"}},e._l(e.identityList,function(e){return s("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})}),1),e._v(" "),s("el-radio",{attrs:{label:"2"},model:{value:e.screenSelect,callback:function(t){e.screenSelect=t},expression:"screenSelect"}},[e._v("账号")]),e._v(" "),s("el-input",{staticClass:"screen-input",attrs:{placeholder:"请输入账号"},model:{value:e.inputAccount,callback:function(t){e.inputAccount=t},expression:"inputAccount"}}),e._v(" "),s("el-radio",{attrs:{label:"3"},model:{value:e.screenSelect,callback:function(t){e.screenSelect=t},expression:"screenSelect"}},[e._v("全部")]),e._v(" "),s("el-button",{staticClass:"start-search",on:{click:e.search}},[e._v("搜索")])],1),e._v(" "),s("el-button",{staticClass:"create-account",on:{click:e.handleAdd}},[e._v("创建账号")])],1)]),e._v(" "),s("div",{staticClass:"out-div"},[s("div",{staticClass:"account-list"},[[s("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData.slice((e.currentPage-1)*e.pagesize,e.currentPage*e.pagesize)}},[s("el-table-column",{attrs:{prop:"id",label:"序号",width:"80"},scopedSlots:e._u([{key:"default",fn:function(t){return[s("span",[e._v(e._s((e.currentPage-1)*e.pagesize+t.$index+1))])]}}])}),e._v(" "),s("el-table-column",{attrs:{prop:"userName",label:"用户姓名"}}),e._v(" "),s("el-table-column",{attrs:{prop:"userAccount",label:"账号"}}),e._v(" "),s("el-table-column",{attrs:{prop:"userType",label:"身份"}}),e._v(" "),s("el-table-column",{attrs:{prop:"institution",label:"所属机构"}}),e._v(" "),s("el-table-column",{attrs:{label:"操作",width:"200px"},scopedSlots:e._u([{key:"default",fn:function(t){return[s("div",{staticClass:"cell"},["平台管理员"!=t.row.userType||t.row.userName==e.username?s("div",{staticClass:"btns edit-btn",on:{click:function(s){return e.handleEdit(t.$index,t.row)}}},[s("img",{attrs:{src:"static/images/edit.png",alt:""}}),e._v(" "),s("p",[e._v("修改")])]):e._e(),e._v(" "),"平台管理员"!=t.row.userType||t.row.userName==e.username?s("div",{staticClass:"btns del-btn",on:{click:function(s){return e.handleDelete(t.$index,t.row)}}},[s("img",{attrs:{src:"static/images/del.png",alt:""}}),e._v(" "),s("p",[e._v("删除")])]):e._e()])]}}])})],1),e._v(" "),s("el-pagination",{staticStyle:{"margin-top":"15px"},attrs:{"current-page":e.currentPage,"page-sizes":[5,10,20,50],"page-size":e.pagesize,layout:"total, sizes, prev, pager, next, jumper",total:e.tableData.length},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}})]],2)])])])]),e._v(" "),s("el-dialog",{attrs:{title:"创建账户",visible:e.dialogVisibleAdd,width:"400px","lock-scroll":!1,"close-on-click-modal":!1,"close-on-press-escape":!1,"show-close":!1},on:{"update:visible":function(t){e.dialogVisibleAdd=t}}},[s("el-form",{ref:"formAdd",attrs:{rules:e.rulesAdd,model:e.formAdd,"label-width":"80px"}},[s("el-form-item",{attrs:{label:"员工姓名",prop:"userName"}},[s("el-input",{model:{value:e.formAdd.userName,callback:function(t){e.$set(e.formAdd,"userName",t)},expression:"formAdd.userName"}})],1),e._v(" "),s("el-form-item",{attrs:{label:"账号",prop:"userAccount"}},[s("el-input",{model:{value:e.formAdd.userAccount,callback:function(t){e.$set(e.formAdd,"userAccount",t)},expression:"formAdd.userAccount"}})],1),e._v(" "),s("el-form-item",{attrs:{label:"密码设置",prop:"password"}},[s("el-input",{model:{value:e.formAdd.password,callback:function(t){e.$set(e.formAdd,"password",t)},expression:"formAdd.password"}})],1),e._v(" "),s("el-form-item",{attrs:{label:"所属机构",prop:"institution"}},[s("el-select",{attrs:{placeholder:"请选择所属机构"},model:{value:e.formAdd.institution,callback:function(t){e.$set(e.formAdd,"institution",t)},expression:"formAdd.institution"}},e._l(e.userTypeList,function(e){return s("el-option",{key:e.nodeName,attrs:{label:e.nodeName,value:e.nodeName}})}),1)],1),e._v(" "),s("el-form-item",{attrs:{label:"身份设置",prop:"userType"}},[s("el-radio-group",{model:{value:e.formAdd.userType,callback:function(t){e.$set(e.formAdd,"userType",t)},expression:"formAdd.userType"}},[s("el-radio",{attrs:{label:100},model:{value:e.form.userType,callback:function(t){e.$set(e.form,"userType",t)},expression:"form.userType"}},[e._v("节点管理员")]),e._v(" "),s("el-radio",{attrs:{label:200},model:{value:e.form.userType,callback:function(t){e.$set(e.form,"userType",t)},expression:"form.userType"}},[e._v("节点普通用户")])],1)],1)],1),e._v(" "),s("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[s("el-button",{staticClass:"submit-btn-style",attrs:{type:"primary"},on:{click:function(t){return e.addAccount("formAdd")}}},[e._v("确 定")]),e._v(" "),s("el-button",{staticClass:"cancle-btn-style",on:{click:function(t){e.dialogVisibleAdd=!1}}},[e._v("取 消")])],1)],1),e._v(" "),s("el-dialog",{attrs:{title:"修改账号",visible:e.dialogVisibleEdit,width:"400px","lock-scroll":!1,"close-on-click-modal":!1,"close-on-press-escape":!1,"show-close":!1},on:{"update:visible":function(t){e.dialogVisibleEdit=t}}},[s("el-form",{ref:"form",attrs:{rules:e.rulesEdit,model:e.form,"label-width":"80px"}},[s("el-form-item",{staticStyle:{display:"none"},attrs:{label:"ID"}},[s("el-input",{model:{value:e.form.id,callback:function(t){e.$set(e.form,"id",t)},expression:"form.id"}})],1),e._v(" "),s("el-form-item",{attrs:{label:"员工姓名",prop:"userName"}},[s("el-input",{model:{value:e.form.userName,callback:function(t){e.$set(e.form,"userName",t)},expression:"form.userName"}})],1),e._v(" "),s("el-form-item",{attrs:{label:"账号",prop:"userAccount"}},[s("el-input",{attrs:{disabled:""},model:{value:e.form.userAccount,callback:function(t){e.$set(e.form,"userAccount",t)},expression:"form.userAccount"}})],1),e._v(" "),s("el-form-item",{attrs:{label:"密码设置",prop:"password"}},[s("el-input",{model:{value:e.form.password,callback:function(t){e.$set(e.form,"password",t)},expression:"form.password"}})],1),e._v(" "),s("el-form-item",{attrs:{label:"所属机构",prop:"institution"}},[s("el-select",{attrs:{placeholder:"请选择所属机构"},model:{value:e.form.institution,callback:function(t){e.$set(e.form,"institution",t)},expression:"form.institution"}},e._l(e.userTypeList,function(e){return s("el-option",{key:e.nodeName,attrs:{label:e.nodeName,value:e.nodeName}})}),1)],1),e._v(" "),s("el-form-item",{attrs:{label:"身份设置",prop:"userType"}},[s("el-radio-group",{model:{value:e.form.userType,callback:function(t){e.$set(e.form,"userType",t)},expression:"form.userType"}},[s("el-radio",{attrs:{label:100},model:{value:e.form.userType,callback:function(t){e.$set(e.form,"userType",t)},expression:"form.userType"}},[e._v("节点管理员")]),e._v(" "),s("el-radio",{attrs:{label:200},model:{value:e.form.userType,callback:function(t){e.$set(e.form,"userType",t)},expression:"form.userType"}},[e._v("节点普通用户")])],1)],1)],1),e._v(" "),s("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[s("el-button",{staticClass:"submit-btn-style",attrs:{type:"primary"},on:{click:function(t){return e.editAccount("form")}}},[e._v("确 定")]),e._v(" "),s("el-button",{staticClass:"cancle-btn-style",on:{click:function(t){e.dialogVisibleEdit=!1}}},[e._v("取 消")])],1)],1),e._v(" "),s("el-dialog",{attrs:{title:"",visible:e.dialogVisibleDel,width:"400px","lock-scroll":!1,"close-on-click-modal":!1,"close-on-press-escape":!1,"show-close":!1},on:{"update:visible":function(t){e.dialogVisibleDel=t}}},[s("img",{staticClass:"del-tip-style",attrs:{src:"static/images/del_tip.png",alt:""}}),e._v(" "),s("p",{staticClass:"del-tip-p"},[e._v("确定删除？")]),e._v(" "),s("el-form",{ref:"formDel",attrs:{model:e.formDel,"label-width":"80px"}},[s("el-form-item",{staticStyle:{display:"none"},attrs:{label:"ID"}},[s("el-input",{model:{value:e.formDel.userAccount,callback:function(t){e.$set(e.formDel,"userAccount",t)},expression:"formDel.userAccount"}})],1)],1),e._v(" "),s("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[s("el-button",{staticClass:"submit-btn-style",attrs:{type:"primary"},on:{click:e.deleteThis}},[e._v("确 定")]),e._v(" "),s("el-button",{staticClass:"cancle-btn-style",on:{click:function(t){e.dialogVisibleDel=!1}}},[e._v("取 消")])],1)],1)],1)],1)],1)},staticRenderFns:[]};var d=s("C7Lr")(c,u,!1,function(e){s("t/Zv")},null,null);t.default=d.exports},"t/Zv":function(e,t){}});
//# sourceMappingURL=5.7c45d4840216b701199a.js.map