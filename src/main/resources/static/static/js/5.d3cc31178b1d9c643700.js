webpackJsonp([5],{ApNQ:function(e,t,s){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a=s("aA9S"),i=s.n(a),n=s("hcrA"),o=s.n(n),l=s("W7zn"),r=s("cosN"),c={name:"senceSet",components:{"nav-header":l.a,"container-top":r.a},data:function(){return{username:Cookies.get("fedname"),token:Cookies.get("fedtoken"),senceList:[],dialogVisibleAdd:!1,formAdd:{},rulesAdd:{sceneName:[{required:!0,message:"请填写场景名称",trigger:"blur"}],target:[{required:!0,message:"请填写目标字段",trigger:"blur"}]},addList:[],dialogVisibleEdit:!1,form:{sceneName:"",newsceneName:"",target:""},rulesEdit:{sceneName:[{required:!0,message:"请选择要修改的场景",trigger:"change"}],newsceneName:[{required:!0,message:"请填写场景名称",trigger:"blur"}],target:[{required:!0,message:"请填写目标字段",trigger:"blur"}]},addListedit:[],editSceneList:[],dialogVisibleDel:!1,formDel:{}}},computed:{classOption:function(){return{singleWidth:0,waitTime:5e3,step:.5,hoverStop:!1,direction:2,limitMoveNum:3}}},mounted:function(){this.findDimensions(),this.getSceneInfo(),this.getmySceneInfo()},methods:{getSceneInfo:function(){var e=this;e.axios.post("/sceneReq",e.qs.stringify({specific:!1,operator:e.username,token:e.token}),{headers:{"Content-Type":"application/x-www-form-urlencoded"}},{emulateJSON:!0}).then(function(t){t.data.message.state?e.senceList=t.data.scenes:e.$message.error(t.data.message)},function(t){e.$message.error(t.response.data.message),401==t.response.status&&(e.$router.push("/"),Cookies.set("fedname",1,{expires:-1}),Cookies.set("fedrole",1,{expires:-1}))})},getmySceneInfo:function(){var e=this;e.axios.post("/sceneReq",e.qs.stringify({specific:!0,operator:e.username,token:e.token}),{headers:{"Content-Type":"application/x-www-form-urlencoded"}},{emulateJSON:!0}).then(function(t){t.data.message.state?e.editSceneList=t.data.scenes:e.$message.error(t.data.message)},function(t){e.$message.error(t.response.data.message),401==t.response.status&&(e.$router.push("/"),Cookies.set("fedname",1,{expires:-1}),Cookies.set("fedrole",1,{expires:-1}))})},handleAdd:function(){this.dialogVisibleAdd=!0,this.addList=[],this.addList.push({value:"",label:"",id:this.randomWord(32,32)},{value:"",label:"",id:this.randomWord(32,32)})},randomWord:function(e,t){for(var s="",a=t?Math.round(Math.random()*(t-e))+e:e,i=["0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"],n=0;n<a;n++){s+=i[Math.round(Math.random()*(i.length-1))]}return s},addThis:function(){this.addList.push({value:"",label:"",id:this.randomWord(32,32)})},delThisCatalog:function(e){var t=this.addList.reduce(function(t,s){return s.id!==e.id&&t.push(s),t},[]);this.addList=t},addAccount:function(e){var t=this;t.$refs[e].validate(function(e){if(!e)return!1;console.log(t.addList);var s=0,a="[";o.a.each(t.addList,function(e,t){""==t.value||""==t.label?s++:a+='{"value":"'+t.value+'","label":"'+t.label+'"},'}),a=a.substring(0,a.length-1)+"]",s>0?t.$message.error("请完善样本释义内容"):(t.dialogVisibleAdd=!1,t.axios.post("/sceneCreate",t.qs.stringify({sceneName:t.formAdd.sceneName,target:t.formAdd.target,operator:t.username,token:t.token,describe:a}),{headers:{"Content-Type":"application/x-www-form-urlencoded"}},{emulateJSON:!0}).then(function(e){e.data.message.state?(t.$message({message:"新增场景成功",type:"success"}),t.getSceneInfo(),t.getmySceneInfo()):t.$message.error(e.data.message)},function(e){t.$message.error(e.response.data.message),401==e.response.status&&(t.$router.push("/"),Cookies.set("fedname",1,{expires:-1}),Cookies.set("fedrole",1,{expires:-1}))}))})},handleEdit:function(e,t){this.dialogVisibleEdit=!0},addThis2:function(){this.addListedit.push({value:"",label:"",id:this.randomWord(32,32)})},delThisCatalog2:function(e){var t=this.addListedit.reduce(function(t,s){return s.id!==e.id&&t.push(s),t},[]);this.addListedit=t},selectScene:function(e){var t;o.a.each(this.editSceneList,function(s,a){console.log(a.sceneName),console.log(e),a.sceneName==e&&(t=a)}),this.form.target=t.target,this.form.newsceneName=t.sceneName,this.addListedit=t.describe},editAccount:function(e){var t=this;t.$refs[e].validate(function(e){if(!e)return!1;var s=0,a="[";o.a.each(t.addListedit,function(e,t){""==t.value||""==t.label?s++:a+='{"value":"'+t.value+'","label":"'+t.label+'"},'}),a=a.substring(0,a.length-1)+"]",s>0?t.$message.error("请完善样本释义内容"):(t.dialogVisibleEdit=!1,t.axios.post("/sceneModify",t.qs.stringify({old_sceneName:t.form.sceneName,sceneName:t.form.newsceneName,target:t.form.target,operator:t.username,token:t.token,describe:a}),{headers:{"Content-Type":"application/x-www-form-urlencoded"}},{emulateJSON:!0}).then(function(e){e.data.message.state?(t.$message({message:"修改场景成功",type:"success"}),t.getSceneInfo(),t.getmySceneInfo()):t.$message.error(e.data.message)},function(e){t.$message.error(e.response.data.message),401==e.response.status&&(t.$router.push("/"),Cookies.set("fedname",1,{expires:-1}),Cookies.set("fedrole",1,{expires:-1}))}))})},handleDelete:function(e,t){this.dialogVisibleDel=!0,this.formDel=i()({},t)},deleteThis:function(){var e=this;e.dialogVisibleDel=!1,e.axios.post("/sceneDelete",e.qs.stringify({sceneName:e.formDel.sceneName,operator:e.username,token:e.token}),{headers:{"Content-Type":"application/x-www-form-urlencoded"}},{emulateJSON:!0}).then(function(t){t.data.message.state?(e.$message({message:"删除场景成功",type:"success"}),e.currentPage=1,e.getSceneInfo(),e.getmySceneInfo()):e.$message.error(t.data.message)},function(t){e.$message.error(t.response.data.message),401==t.response.status&&(e.$router.push("/"),Cookies.set("fedname",1,{expires:-1}),Cookies.set("fedrole",1,{expires:-1}))})},findDimensions:function(){window.innerWidth?this.winWidth=window.innerWidth:document.body&&document.body.clientWidth&&(this.winWidth=document.body.clientWidth),window.innerHeight?this.winHeight=window.innerHeight:document.body&&document.body.clientHeight&&(this.winHeight=document.body.clientHeight),document.documentElement&&document.documentElement.clientHeight&&document.documentElement.clientWidth&&(this.winHeight=document.documentElement.clientHeight,this.winWidth=document.documentElement.clientWidth),o()(".sence-list").attr("style","min-height:"+(this.winHeight-290)+"px;")}}},d={render:function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("el-container",[s("el-header",[s("container-top")],1),e._v(" "),s("el-container",[s("nav-header"),e._v(" "),s("el-main",[s("el-container",{staticClass:"container-main-div"},[s("div",{staticClass:"container-div"},[s("div",{staticClass:"container-main"},[s("div",{staticClass:"top-main"},[s("div",{staticClass:"page-top-line"},[s("div",{staticClass:"page-title"},[e._v("场景设置")])]),e._v(" "),s("div",{staticClass:"screen-block"},[s("el-button",{staticClass:"submit-btn-style",attrs:{icon:"el-icon-plus"},on:{click:e.handleAdd}},[e._v("新增场景")]),e._v(" "),s("el-button",{staticClass:"submit-btn-style",attrs:{icon:"el-icon-edit"},on:{click:e.handleEdit}},[e._v("修改场景")]),e._v(" "),s("el-button",{staticClass:"yellow-btn-style",attrs:{icon:"el-icon-delete"},on:{click:e.handleDelete}},[e._v("删除场景")])],1)]),e._v(" "),s("div",{staticClass:"out-div"},[s("div",{staticClass:"sence-list"},[[s("el-row",{staticClass:"row-div",attrs:{gutter:30}},e._l(e.senceList,function(t,a){return s("el-col",{key:t.institution,attrs:{span:8}},[s("div",{staticClass:"grid-content bg-purple"},[s("div",{staticClass:"sence-nodeinfo"},[s("img",{staticStyle:{border:"1px solid #dcdfe6","border-radius":"3px"},attrs:{src:t.logo,alt:""}}),e._v(" "),s("p",{staticClass:"nodename"},[e._v(e._s(t.institution))])]),e._v(" "),s("div",{staticClass:"sencename",class:"sencetype"+a%3},[e._v(e._s(t.sceneName))]),e._v(" "),s("div",{staticClass:"goal-info"},[s("img",{attrs:{src:"static/images/goal.png",alt:""}}),e._v(" "),s("p",[e._v("目标字段:"+e._s(t.target))])]),e._v(" "),s("vue-seamless-scroll",{staticClass:"table-content",attrs:{data:t.describe,"class-option":e.classOption}},e._l(t.describe,function(t){return s("div",{staticClass:"one-describe"},[s("span",[e._v(e._s(t.value))]),e._v(" "),s("span",[e._v(e._s(t.label))])])}),0)],1)])}),1)]],2)])])])]),e._v(" "),s("el-dialog",{attrs:{title:"新增场景",visible:e.dialogVisibleAdd,width:"600px","lock-scroll":!1,"close-on-click-modal":!1,"close-on-press-escape":!1,"show-close":!1},on:{"update:visible":function(t){e.dialogVisibleAdd=t}}},[s("el-form",{ref:"formAdd",attrs:{rules:e.rulesAdd,model:e.formAdd,"label-width":"120px"}},[s("el-form-item",{attrs:{label:"场景名称",prop:"sceneName"}},[s("el-input",{model:{value:e.formAdd.sceneName,callback:function(t){e.$set(e.formAdd,"sceneName",t)},expression:"formAdd.sceneName"}})],1),e._v(" "),s("el-form-item",{attrs:{label:"目标字段",prop:"target"}},[s("el-input",{model:{value:e.formAdd.target,callback:function(t){e.$set(e.formAdd,"target",t)},expression:"formAdd.target"}})],1),e._v(" "),s("el-form-item",{attrs:{label:"样本释义"}},[s("el-button",{attrs:{size:"small",type:"primary",icon:"el-icon-plus"},on:{click:e.addThis}})],1),e._v(" "),s("el-form-item",{attrs:{label:""}},[s("table",[s("thead",[s("tr",[s("th",[e._v("数据")]),e._v(" "),s("th",[e._v("释义")]),e._v(" "),s("th")])]),e._v(" "),s("tbody",e._l(e.addList,function(t,a){return s("tr",{staticClass:"addList"},[s("td",[s("input",{directives:[{name:"model",rawName:"v-model",value:t.value,expression:"item.value"}],attrs:{type:"text",placeholder:"请输入数据"},domProps:{value:t.value},on:{input:function(s){s.target.composing||e.$set(t,"value",s.target.value)}}})]),e._v(" "),s("td",[s("input",{directives:[{name:"model",rawName:"v-model",value:t.label,expression:"item.label"}],attrs:{type:"text",placeholder:"请输入释义"},domProps:{value:t.label},on:{input:function(s){s.target.composing||e.$set(t,"label",s.target.value)}}})]),e._v(" "),s("td",[s("img",{attrs:{src:"static/images/del.png",alt:""},on:{click:function(s){return e.delThisCatalog(t)}}})])])}),0)])])],1),e._v(" "),s("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[s("el-button",{staticClass:"submit-btn-style",attrs:{type:"primary"},on:{click:function(t){return e.addAccount("formAdd")}}},[e._v("确 定")]),e._v(" "),s("el-button",{staticClass:"cancle-btn-style",on:{click:function(t){e.dialogVisibleAdd=!1}}},[e._v("取 消")])],1)],1),e._v(" "),s("el-dialog",{attrs:{title:"修改场景",visible:e.dialogVisibleEdit,width:"600px","lock-scroll":!1,"close-on-click-modal":!1,"close-on-press-escape":!1,"show-close":!1},on:{"update:visible":function(t){e.dialogVisibleEdit=t}}},[s("el-form",{ref:"form",attrs:{rules:e.rulesEdit,model:e.form,"label-width":"120px"}},[s("el-form-item",{attrs:{label:"场景名称",prop:"sceneName"}},[s("el-select",{staticClass:"screen-input",attrs:{placeholder:"请选择场景"},on:{change:e.selectScene},model:{value:e.form.sceneName,callback:function(t){e.$set(e.form,"sceneName",t)},expression:"form.sceneName"}},e._l(e.editSceneList,function(e){return s("el-option",{key:e.sceneName,attrs:{label:e.sceneName,value:e.sceneName}})}),1)],1),e._v(" "),s("el-form-item",{attrs:{label:"新名称",prop:"newsceneName"}},[s("el-input",{model:{value:e.form.newsceneName,callback:function(t){e.$set(e.form,"newsceneName",t)},expression:"form.newsceneName"}})],1),e._v(" "),s("el-form-item",{attrs:{label:"目标字段",prop:"target"}},[s("el-input",{model:{value:e.form.target,callback:function(t){e.$set(e.form,"target",t)},expression:"form.target"}})],1),e._v(" "),s("el-form-item",{attrs:{label:"样本释义"}},[s("el-button",{attrs:{size:"small",type:"primary",icon:"el-icon-plus"},on:{click:e.addThis2}})],1),e._v(" "),s("el-form-item",{attrs:{label:""}},[s("table",[s("thead",[s("tr",[s("th",[e._v("数据")]),e._v(" "),s("th",[e._v("释义")]),e._v(" "),s("th")])]),e._v(" "),s("tbody",e._l(e.addListedit,function(t,a){return s("tr",{staticClass:"addList"},[s("td",[s("input",{directives:[{name:"model",rawName:"v-model",value:t.value,expression:"item.value"}],attrs:{type:"text",placeholder:"请输入数据"},domProps:{value:t.value},on:{input:function(s){s.target.composing||e.$set(t,"value",s.target.value)}}})]),e._v(" "),s("td",[s("input",{directives:[{name:"model",rawName:"v-model",value:t.label,expression:"item.label"}],attrs:{type:"text",placeholder:"请输入释义"},domProps:{value:t.label},on:{input:function(s){s.target.composing||e.$set(t,"label",s.target.value)}}})]),e._v(" "),s("td",[s("img",{attrs:{src:"static/images/del.png",alt:""},on:{click:function(s){return e.delThisCatalog2(t)}}})])])}),0)])])],1),e._v(" "),s("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[s("el-button",{staticClass:"submit-btn-style",attrs:{type:"primary"},on:{click:function(t){return e.editAccount("form")}}},[e._v("确 定")]),e._v(" "),s("el-button",{staticClass:"cancle-btn-style",on:{click:function(t){e.dialogVisibleEdit=!1}}},[e._v("取 消")])],1)],1),e._v(" "),s("el-dialog",{attrs:{title:"删除场景",visible:e.dialogVisibleDel,width:"400px","lock-scroll":!1,"close-on-click-modal":!1,"close-on-press-escape":!1,"show-close":!1},on:{"update:visible":function(t){e.dialogVisibleDel=t}}},[s("el-form",{ref:"formDel",attrs:{model:e.formDel,"label-width":"80px"}},[s("el-form-item",{attrs:{label:"场景名称",prop:"sceneName"}},[s("el-select",{staticClass:"screen-input",attrs:{placeholder:"请选择场景"},model:{value:e.formDel.sceneName,callback:function(t){e.$set(e.formDel,"sceneName",t)},expression:"formDel.sceneName"}},e._l(e.editSceneList,function(e){return s("el-option",{key:e.sceneName,attrs:{label:e.sceneName,value:e.sceneName}})}),1)],1)],1),e._v(" "),s("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[s("el-button",{staticClass:"submit-btn-style",attrs:{type:"primary"},on:{click:e.deleteThis}},[e._v("确 定")]),e._v(" "),s("el-button",{staticClass:"cancle-btn-style",on:{click:function(t){e.dialogVisibleDel=!1}}},[e._v("取 消")])],1)],1)],1)],1)],1)},staticRenderFns:[]};var m=s("C7Lr")(c,d,!1,function(e){s("tl7I")},null,null);t.default=m.exports},tl7I:function(e,t){}});
//# sourceMappingURL=5.d3cc31178b1d9c643700.js.map