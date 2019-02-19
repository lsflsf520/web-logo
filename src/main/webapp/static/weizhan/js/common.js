(function(){
    var html = '<div class="zx_xintx show_hide">'+
        '<div class="mask"></div>'+
        '<div class="tx_bskuank">'+
        '<div class="nk_top">填写咨询信息<i></i></div>'+
        '<div class="nk_bot">'+
        '<div class="ot_phone ty_shur">'+
        '<span>手机号</span>'+
        '<input type="text"/>'+
        '</div>'+
        '<div class="ot_sex">'+
        '<span>性别</span>'+
        '<div>'+
        '<div class="hover">男</div>'+
        '<div>女</div>'+
        '</div>'+
        '</div>'+
        '<div class="ot_hobby">'+
        '<span>兴趣爱好</span>'+
        '<div>'+
        '<div class="hover">乒乓球</div>'+
        '<div>足球</div>'+
        '</div>'+
        '</div>'+
        '<div class="ot_qq ty_shur">'+
        '<span>QQ</span>'+
        '<input type="text"/>'+
        '</div>'+
        '<div class="ot_category">'+
        '<span>咨询类别</span>'+
        '<div>'+
        '<select>'+
        '<option value ="volvo">Volvo</option>'+
        '<option value ="saab">Saab</option>'+
        '<option value="opel">Opel</option>'+
        '<option value="audi">Audi</option>'+
        '</select>'+
        '<i></i>'+
        '</div>'+
        '</div>'+
        '<div class="ot_supplement">'+
        '<span>补充信息</span>'+
        '<textarea name="" id="" placeholder="多行输入"></textarea>'+
        '</div>'+
        '<div class="ot_sbbtn"><button>提交</button></div>'+
        '</div>'+
        '</div>'+
        '</div>';
    $('body').append(html);
})();
$('.uc_qieh').on('click','div',function(){
    $(this).addClass('hover').siblings().removeClass('hover');
   if($(this).index()==0){
       $('.uc_neironx').show();
       $('.gz_liuchent').hide();
   }else{
       $('.uc_neironx').hide();
       $('.gz_liuchent').show();
   }
});
$('.zx_xintx .nk_bot .ot_sex>div').on('click','div',function(){
   $(this).addClass('hover').siblings().removeClass('hover');
});
$('.zx_xintx .nk_bot .ot_hobby>div').on('click','div',function(){
    $(this).toggleClass('hover');
});
$('.lj_zhixun').on('click',function(){
   $('.zx_xintx').toggle(0,'.show_hide')
});
$('.tx_bskuank .nk_top').on('click','i',function(){
    $('.zx_xintx').toggle(0,'.show_hide')
});
$('.kf_liaojie').on('click','i',function(e){
    e.preventDefault();
    $('.kf_liaojie').toggle(0,'.show_hide')
});