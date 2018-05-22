var editor;//编辑器
KindEditor.ready(function(K) {
	editor = K.create('#editor_id',{
        themeType : 'simple',
        items : ['undo', 'redo', 'removeformat', '|', 'formatblock', 'fontname', 'fontsize', 'bold',
 				'italic', 'underline', 'strikethrough', '|','forecolor', 'hilitecolor', '|',  
 				'justifyleft', 'justifycenter', 'justifyright', 'justifyfull','|', 'insertorderedlist', 
 				'insertunorderedlist', 'indent', 'outdent', 'subscript', 'superscript', '/',
 				'cut', 'copy', 'paste', 'plainpaste', '|','image','insertfile', 'table', 
 				'hr', 'link', 'unlink', '|', 'source']
    });
});