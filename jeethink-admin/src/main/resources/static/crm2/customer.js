/*客户列表-详细*/
function detail(id) {
    var url = prefix + '/detail/' + id;
    $.modal.openTab("查看客户详情", url);
}

/* 共享客户 */
function share(isShare) {
    var customerId = $.table.selectColumns("customerId");
    var belongTos = $.table.selectColumnsNoUnique("belongTo");
    if (customerId.length == 0) {
        $.modal.alertWarning("请至少选择一条记录");
        return;
    }
    if (belongTos.length == 0) {
        $.modal.msgWarning("公共客户无法进行共享设置！");
        return;
    }
    for (var i = 0; i < belongTos.length; i++) {
        if (belongTos[i] == "") {
            $.modal.msgWarning("公共客户无法进行共享设置！");
            return;
        }
    }

    if (isShare == 1) {
        var url = ctx + 'crm2/share/sharePerson?customerId=' + customerId + "&isShare=" + isShare;
        $.modal.open("共享给其他人员", url);
    } else {
        $.operate.post(prefix + "/share"+'?customerId=' + customerId + "&isShare=" + isShare);

    }

}



/* 转交给其他人 */
function transferPerson() {
    var customerIds = $.table.selectColumns("customerId");
    if (customerIds.length == 0) {
        $.modal.alertWarning("请至少选择一条记录");
        return;
    }
    var url = ctx + 'crm2/transfer/transferPerson?ids=' + customerIds.join() + "&businessType=Customer";
    $.modal.open("转给其它团队成员（变更负责人）", url);
}

/* 转为公共客户 */
function transferPublic() {
    var customerIds = $.table.selectColumns("customerId");
    var belongTos = $.table.selectColumnsNoUnique("belongTo");
    if (customerIds.length == 0) {
        $.modal.alertWarning("请至少选择一条记录");
        return;
    }
    for (var i = 0; i < belongTos.length; i++) {
        if (belongTos[i] == "") {
            $.modal.msgWarning("公共客户无法再次转交为公共客户！");
            return;
        }
    }
    var url = ctx + 'crm2/transfer/transferPublic?ids=' + customerIds.join() + "&businessType=Customer";
    $.modal.open("转为公共客户", url);
}

/* 领取客户 */
function get() {
    var customerIds = $.table.selectColumns("customerId");
    if (customerIds.length == 0) {
        $.modal.alertWarning("请至少选择一条记录");
        return;
    }
    var url = ctx + 'crm2/transfer/getPublic?ids=' + customerIds.join() + "&businessType=Customer";
    $.modal.open("领取公共客户", url);
}
