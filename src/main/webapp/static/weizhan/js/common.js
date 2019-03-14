$(function() {
	$('.uc_qieh').on('click', 'div', function() {
		$(this).addClass('hover').siblings().removeClass('hover');
		if ($(this).index() == 0) {
			$('.uc_neironx').show();
			$('.gz_liuchent').hide();
		} else {
			$('.uc_neironx').hide();
			$('.gz_liuchent').show();
		}
	});
	$('.zx_xintx .nk_bot .ot_sex>div').on('click', 'div', function() {
		$(this).addClass('hover').siblings().removeClass('hover');
	});
	$('.zx_xintx .nk_bot .ot_hobby>div').on('click', 'div', function() {
		$(this).toggleClass('hover');
	});
	$('.lj_zhixun,#ljzx').on('click', function() {
		$('.zx_xintx').toggle(0, '.show_hide')
	});
	$('#closeI').click(function() {
		$('.zx_xintx').css('display', 'none');
	});
	/*
	 * $('.tx_bskuank .nk_top').on('click', 'i', function() {
	 * $('.zx_xintx').toggle(0, '.show_hide') });
	 */
	$('.kf_liaojie').on('click', 'i', function(e) {
		e.preventDefault();
		$('.kf_liaojie').toggle(0, '.show_hide')
	});
})
