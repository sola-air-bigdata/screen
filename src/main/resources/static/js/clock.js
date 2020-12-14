class Clock {
    constructor(dom, formatStr) {
        // let d = new Date(startTimeStr).toString();
        // this.startTimeStr = d !== "Invalid Date" ? d : new Date().toString();
        this.startTimeStr = '';
        this.formatStr = formatStr ? formatStr : 'YYYY-MM-DD hh:mm:ss';
        this.timer = null;
        this.dom = dom;
        this.regArr = ['YYYY', 'MM', 'DD', 'hh', 'mm', 'ss'];
    }
    _getTimeDetail() {
        const d = new Date();
        const yy = d.getFullYear(), MM = d.getMonth() + 1, dd = d.getDate(), hh = d.getHours(), mm = d.getMinutes(), ss = d.getSeconds();
        const year = yy.toString(),
            month = MM + 1 < 10 ? "0" + MM.toString() : MM.toString(),
            date = dd < 10 ? "0" + dd.toString() : dd.toString(),
            hour = hh < 10 ? "0" + hh.toString() : hh.toString(),
            minute = mm < 10 ? "0" + mm.toString() : mm.toString(),
            second = ss < 10 ? "0" + ss.toString() : ss.toString();
            
        
        return {
            'YYYY': year,
            'MM': month,
            'DD': date,
            'hh': hour,
            'mm': minute,
            'ss': second
        };
    }
    _format() {
        const d = this._getTimeDetail();
        let f = this.formatStr;
        for (let i = 0; i < this.regArr.length; i++) {
            f = f.replace(this.regArr[i], d[this.regArr[i]]);
        }
        this.startTimeStr = f;
    }
    start() {
        this.renderDom();
        this.timer = setInterval(() => {
            this.renderDom();
        }, 1000);
    }

    renderDom() {
        if (this.timer !== null) {
            this._format();
            this.dom.innerText = this.startTimeStr;
        }
    }

    destroy() {
        clearInterval(this.timer);
        this.timer = null;
    }
}

(function () {
    var clock = new Clock(document.getElementById('time'));
    clock.start();
})();



