$(function(){
	
	//富文本编辑器
	UE.getEditor('myEditor')
	
	UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
	UE.Editor.prototype.getActionUrl = function(action){
		if(action == '/admin/upload/image'){
			return basePath+'admin/upload/image';
		}else{
			return this._bkGetActionUrl.call(this, action);
		}
	}
});