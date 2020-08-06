webpackJsonp([9],{Np5I:function(e,t,o){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var s=o("hcrA"),i=o.n(s),a=o("W7zn"),n=o("cosN"),r={name:"nodeSet",components:{"nav-header":a.a,"container-top":n.a},data:function(){return{username:Cookies.get("fedname"),token:Cookies.get("fedtoken"),form:{nodeName:"",ipAddress:"",port:"",CSV_path:"",logo:""},oldName:"",headImg:"",rulesEdit:{nodeName:[{required:!0,message:"请填写节点名称",trigger:"blur"}],ipAddress:[{required:!0,message:"请填写IP地址",trigger:"blur"}],port:[{required:!0,message:"请填写端口号",trigger:"blur"}],CSV_path:[{required:!0,message:"请填写CSV文件路径",trigger:"blur"}]}}},mounted:function(){this.findDimensions(),this.getNodeInfo()},methods:{getNodeInfo:function(){var e=this;e.axios.post("/operatorInfoReq",e.qs.stringify({operator:e.username}),{headers:{"Content-Type":"application/x-www-form-urlencoded"}},{emulateJSON:!0}).then(function(t){t.data.message.state?(e.form.nodeName=t.data.nodeName,e.form.ipAddress=t.data.ipAddress,e.form.port=t.data.port,e.form.CSV_path=t.data.CSV_path,e.headImg=t.data.logo,e.oldName=t.data.nodeName,t.data.logo&&i()("#base64Img").show()):e.$message.error(t.data.message)},function(t){e.$message.error(t.response.data.message),401==t.response.status&&(e.$router.push("/"),Cookies.set("fedname",1,{expires:-1}),Cookies.set("fedrole",1,{expires:-1}))})},imgChange:function(){var e=this,t=document.querySelector(".add-uploadimg input[type=file]").files[0];if(console.log(t.size),t.size>=1024)this.$message({message:"文件大小超出1K上限",type:"error"});else{var o=new FileReader;o.onloadend=function(){i()("#base64Img").show(),e.headImg=o.result},t&&o.readAsDataURL(t)}},submitDataset:function(){var e=this;e.axios.post("/nodeModify",e.qs.stringify({old_nodeName:e.oldName,nodeName:e.form.nodeName,ipAddress:e.form.ipAddress||"",port:e.form.port||"",CSV_path:e.form.CSV_path||"",logo:e.headImg||"",operator:e.username,token:e.token}),{headers:{"Content-Type":"application/x-www-form-urlencoded"}},{emulateJSON:!0}).then(function(t){t.data.message.state?(e.$message({message:"修改成功",type:"success"}),e.currentPage=1,e.getNodeInfo()):e.$message.error(t.data.message)},function(t){e.$message.error(t.response.data.message),401==t.response.status&&(e.$router.push("/"),Cookies.set("fedname",1,{expires:-1}),Cookies.set("fedrole",1,{expires:-1}))})},findDimensions:function(){window.innerWidth?this.winWidth=window.innerWidth:document.body&&document.body.clientWidth&&(this.winWidth=document.body.clientWidth),window.innerHeight?this.winHeight=window.innerHeight:document.body&&document.body.clientHeight&&(this.winHeight=document.body.clientHeight),document.documentElement&&document.documentElement.clientHeight&&document.documentElement.clientWidth&&(this.winHeight=document.documentElement.clientHeight,this.winWidth=document.documentElement.clientWidth),i()(".nodeset-list").attr("style","min-height:"+(this.winHeight-290)+"px;")}}},d={render:function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("el-container",[o("el-header",[o("container-top")],1),e._v(" "),o("el-container",[o("nav-header"),e._v(" "),o("el-main",[o("el-container",{staticClass:"container-main-div"},[o("div",{staticClass:"container-div"},[o("div",{staticClass:"container-main"},[o("div",{staticClass:"top-main"},[o("div",{staticClass:"page-top-line"},[o("div",{staticClass:"page-title"},[e._v("节点设置")])])]),e._v(" "),o("div",{staticClass:"out-div"},[o("div",{staticClass:"nodeset-list"},[[o("el-form",{ref:"form",staticStyle:{width:"400px","text-align":"center"},attrs:{rules:e.rulesEdit,model:e.form,"label-width":"120px"}},[o("el-form-item",{attrs:{label:"节点名称",prop:"nodeName"}},[o("el-input",{model:{value:e.form.nodeName,callback:function(t){e.$set(e.form,"nodeName",t)},expression:"form.nodeName"}})],1),e._v(" "),o("el-form-item",{staticStyle:{width:"400px"},attrs:{label:"机构logo"}},[o("img",{attrs:{id:"base64Img",src:e.headImg}}),e._v(" "),o("div",{staticClass:"img-div"},[o("p",[e._v("选择图片")]),e._v(" "),o("el-input",{staticClass:"add-uploadimg",staticStyle:{opacity:"0"},attrs:{type:"file"},on:{change:e.imgChange},model:{value:e.form.logo,callback:function(t){e.$set(e.form,"logo",t)},expression:"form.logo"}})],1)]),e._v(" "),o("el-form-item",{attrs:{label:"IP地址",prop:"ipAddress"}},[o("el-input",{model:{value:e.form.ipAddress,callback:function(t){e.$set(e.form,"ipAddress",t)},expression:"form.ipAddress"}})],1),e._v(" "),o("el-form-item",{attrs:{label:"端口号",prop:"port"}},[o("el-input",{model:{value:e.form.port,callback:function(t){e.$set(e.form,"port",t)},expression:"form.port"}})],1),e._v(" "),o("el-form-item",{attrs:{label:"CSV文件路径",prop:"CSV_path"}},[o("el-input",{model:{value:e.form.CSV_path,callback:function(t){e.$set(e.form,"CSV_path",t)},expression:"form.CSV_path"}})],1),e._v(" "),o("el-button",{staticClass:"submit-btn-style",on:{click:e.submitDataset}},[e._v("重新设置")])],1)]],2)])])])])],1)],1)],1)},staticRenderFns:[]};var m=o("C7Lr")(r,d,!1,function(e){o("ioXi")},null,null);t.default=m.exports},ioXi:function(e,t){}});
//# sourceMappingURL=9.4487b4d5aa2eae401ecf.js.map