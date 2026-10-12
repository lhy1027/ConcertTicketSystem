<template>
	<div>
	<!--  -->
		<div class="breadcrumb-preview">
			<el-breadcrumb :separator="'>'">
				<el-breadcrumb-item class="item1" to="/"><a>首页</a></el-breadcrumb-item>
				<el-breadcrumb-item class="item2" v-for="(item, index) in breadcrumbItem" :key="index" :to="'/index/piaowuxinxi?centerType=' + (centerType?'1':'0')"><a>{{item.name}}</a></el-breadcrumb-item>
				<el-breadcrumb-item class="item3"><a href="javascript:void(0);">详情</a></el-breadcrumb-item>
			</el-breadcrumb>
		</div>
		<div class="back_box">
			<el-button class="backBtn" size="mini" @click="backClick">
				<span class="icon iconfont icon-jiantou33"></span>
				<span>返回</span>
			</el-button>
		</div>
		<div class="detail-preview">
			<div class="attr">
				<div class="info">
					<div class="title-item">
						<div class="detail-title">
							{{detail.piaowumingcheng}}
						</div>
						<div class="colectBtn" @click="storeup(1)" v-show="!isStoreup">
							<i class="icon iconfont icon-shoucang10"></i>
							<span class="text">收藏({{detail.storeupnum}})</span>
						</div>
						<div class="colectBtnActive" @click="storeup(-1)" v-show="isStoreup">
							<i class="icon iconfont icon-shoucang12"></i>
							<span class="text">取消已收藏({{detail.storeupnum}})</span>
						</div>
					</div>
					<div class="item" v-if="detail.price">
						<div class="lable">价格</div>
						<div class="text price bold"><span style="font-size: 12px">￥</span>{{detail.price}}</div>
					</div>
					<div class="item" v-if="detail.jf">
						<div class="lable">积分</div>
						<div class="text price bold">{{detail.jf}}</div>
					</div>
					<div class="item">
						<div class="lable">单限</div>
						<div class="text">{{detail.onelimittimes}}</div>
					</div>
					<div class="item">
						<div class="lable">库存</div>
						<div class="text">{{detail.alllimittimes}}</div>
					</div>
					<div class="item">
						<div class="lable">票务类型</div>
						<div class="text "  >{{detail.piaowuleixing}}</div>
					</div>
					<div class="item">
						<div class="lable">演出日期</div>
						<div class="text "  >{{detail.yanchuriqi}}</div>
					</div>
					<div class="item">
						<div class="lable">演出时间</div>
						<div class="text "  >{{detail.yanchushijian}}</div>
					</div>
					<div class="item">
						<div class="lable">结束时间</div>
						<div class="text "  >{{detail.jieshushijian}}</div>
					</div>
					<div class="item">
						<div class="lable">演出城市</div>
						<div class="text "  >{{detail.yanchuchengshi}}</div>
					</div>
					<div class="item">
						<div class="lable">座位类型</div>
						<div class="text "  >{{detail.zuoweileixing}}</div>
					</div>
					<div class="item">
						<div class="lable">座位总数</div>
						<div class="text "  >{{detail.number}}</div>
					</div>
					<div class="btn_box">

						<el-button class="reserveBtn" type="warning" size="small" @click="bookClick">立即预订</el-button>
					</div>
					<div class="btn_box">
						<el-button class="editBtn" v-if="btnAuth('piaowuxinxi','修改')" @click="editClick">修改</el-button>
						<el-button class="delBtn" v-if="btnAuth('piaowuxinxi','删除')" @click="delClick">删除</el-button>
					</div>
				</div>
			</div>
		
			<el-carousel v-if="detailBanner.length" trigger="click" indicator-position="inside" arrow="always" type="default" direction="horizontal" height="480px" :autoplay="true" :interval="3000" :loop="true">
				<el-carousel-item v-for="item in detailBanner" :key="item.id">
					<img :preview-src-list="[item]" v-if="item.substr(0,4)=='http'" :src="item" class="image">
					<img :preview-src-list="[baseUrl + item]" v-else :src="baseUrl + item" class="image">
				</el-carousel-item>
			</el-carousel>


		

		
			<el-tabs class="detail-tabs" v-model="activeName" type="border-card">
				<el-tab-pane label="乐队介绍" name="first">
					<div v-html="detail.piaowujieshao"></div>
				</el-tab-pane>
				<el-tab-pane label="选座" name="last">
					<div class="seat-list">
						<div v-for="(item, index) in numberList" v-bind:key="index" class="seat-item">
							<img @click="selectTip()" v-if="item.select" class="seat-icon" src="../../assets/selected.png" />
							<img @click="selectSeat(item)" v-else-if="!item.active" class="seat-icon" src="../../assets/unselect.png" />
							<img @click="unselectSeat(item)" v-else class="seat-icon" src="../../assets/select.png" />
							<span>{{item.name}}</span>
						</div>
					</div>
				</el-tab-pane>
			</el-tabs>

		</div>
		<div class="share_view">
		</div>
	</div>
</template>

<script>
	import axios from 'axios'
	import Swiper from "swiper";
	export default {
		//数据集合
		data() {
			return {
				tablename: 'piaowuxinxi',
				baseUrl: '',
				breadcrumbItem: [
					{
						name: '票务信息'
					}
				],
				title: '',
				detailBanner: [],
				userid: localStorage.getItem('frontUserid'),
				id: 0,
				detail: {},
				activeName: 'first',
				total: 1,
				pageSize: 5,
				totalPage: 1,
				storeupParams: {
					name: '',
					picture: '',
					refid: 0,
					tablename: 'piaowuxinxi',
					userid: localStorage.getItem('frontUserid')
				},
				isStoreup: false,
				storeupInfo: {},
				buynumber: 1,
				numberList: [],
				centerType: false,
				shareUrl: location.href,
			}
		},
		created() {
			if(this.$route.query.centerType) {
				this.centerType = true
			}
			
			this.init();
		},
		mounted() {
		},
		//方法集合
		methods: {
			init() {
				this.id = this.$route.query.id
				this.baseUrl = this.$config.baseUrl;
				this.$http.get(this.tablename + '/detail/'  + this.id, {}).then(res => {
					if (res.data.code == 0) {
						this.detail = res.data.data;
						this.title = this.detail.piaowumingcheng;
						this.detailBanner = this.detail.tupian ? this.detail.tupian.split(",") : [];
						this.$forceUpdate();
						if(localStorage.getItem('frontToken')){
							this.getStoreupStatus();
						}

						let selectArray = this.detail.selected?this.detail.selected.split(','):[];
						for (let i = 1; i <= this.detail.number; i++) {
							this.numberList.push({
								name: `${i}号`,
								select: false,
								active: false
							});
						}
						if(selectArray.length){
							for (let i = 0; i < selectArray.length; i++) {
								this.numberList[selectArray[i]-1].select = true;
							}
						}
			
					}
				});
			},
			createOrder() {
				let order = '';
				let now = new Date();
				order += now.getFullYear();
				order += now.getMonth() + 1;
				order += now.getDate();
				order += now.getHours();
				order += now.getMinutes();
				order += now.getSeconds();
				order += now.getMilliseconds();
				return order;
			},
			bookClick() {
				let activeSeat = [];
				for(let i=0;i<this.numberList.length;i++){
					if(this.numberList[i].active){
						activeSeat.push(this.numberList[i].name.replace('号',''));
					}
				}
				if (activeSeat.length==0) {
					this.$message({
					  type: 'error',
					  message: '请选择要预定的位置!',
					  duration: 1500
					});
					return
				}
				let data = {
					orderid: this.createOrder(),
					tablename: this.tablename,
					userid: localStorage.getItem('frontUserid'),
					goodid: this.detail.id,
					goodname: this.title,
					picture:this.detailBanner[0],
					buynumber: activeSeat.length,
					total: 0,
					discounttotal: 0,
					address: activeSeat.join(','),
					activeSeat: activeSeat.join(','),
					status: '已支付',
					discountprice: this.detail.vipprice 
				}
				if (this.detail.price) {
					data['status'] = '未支付'
					data['price'] = this.detail.price
					data['total'] = parseFloat(data['price'] * activeSeat.length).toFixed(2)
					localStorage.setItem('orderGoods', JSON.stringify([data]));
					// 跳转到确认下单页面
					let query = {
						type: 1,
						seat: 1
					}
					this.$confirm('是否确认预定？').then(_ => {
						this.$router.push({path: '/index/shop-order/orderConfirm', query: query});
					}).catch(_ => {});
				} else {
					data['status'] = '已支付'
					data['price'] = 0
					data['total'] = parseFloat(data['price'] * activeSeat.length).toFixed(2)
					localStorage.setItem('orderGoods', JSON.stringify([data]));
					// 跳转到确认下单页面
					let query = {
						type: 1,
						seat: 1
					}
					this.$confirm('是否确认预定？').then(_ => {
						this.$router.push({path: '/index/shop-order/orderConfirm', query: query});
					}).catch(_ => {});
				}
			},
			selectTip() {
				this.$message({
					type: 'error',
					message: '该座位已被预定!',
					duration: 1500
				});
			},
			selectSeat(item) {
				item.active = true;
			},
			unselectSeat(item) {
				item.active = false;
			},
			storeup(type) {
				if (type == 1 && !this.isStoreup) {
					this.storeupParams.name = this.title;
					this.storeupParams.picture = this.detailBanner[0];
					this.storeupParams.refid = this.detail.id;
					this.storeupParams.type = type;
					this.$http.post('storeup/add', this.storeupParams).then(res => {
						if (res.data.code == 0) {
							this.isStoreup = true;
							this.detail.storeupnum++
							this.$http.post('piaowuxinxi/update', this.detail).then(res => {});
							this.$message({
								type: 'success',
								message: '收藏成功!',
								duration: 1500,
							});
						}
					});
				}
				if (type == -1 && this.isStoreup) {
					this.$http.get('storeup/list', {params: {page: 1, limit: 1, type: 1, refid: this.detail.id, tablename: 'piaowuxinxi', userid: localStorage.getItem('frontUserid')}}).then(res => {
						if (res.data.code == 0 && res.data.data.list.length > 0) {
							this.isStoreup = true;
							this.storeupInfo = res.data.data.list[0];
							let delIds = new Array();
							delIds.push(this.storeupInfo.id);
							this.$http.post('storeup/delete', delIds).then(res => {
								if (res.data.code == 0) {
									this.isStoreup = false;
									this.detail.storeupnum--
									this.$http.post('piaowuxinxi/update', this.detail).then(res => {});
									this.$message({
										type: 'success',
										message: '取消成功!',
										duration: 1500,
									});
								}
							});
						}
					});
				}
			},
			getStoreupStatus(){
				if(localStorage.getItem("frontToken")) {
					this.$http.get('storeup/list', {params: {page: 1, limit: 1, type: 1, refid: this.detail.id, tablename: 'piaowuxinxi', userid: localStorage.getItem('frontUserid')}}).then(res => {
						if (res.data.code == 0 && res.data.data.list.length > 0) {
							this.isStoreup = true;
							this.storeupInfo = res.data.data.list[0];
						}
					});
				}
			},
			curChange(page) {
				this.getDiscussList(page);
			},
			prevClick(page) {
				this.getDiscussList(page);
			},
			nextClick(page) {
				this.getDiscussList(page);
			},
			sizeChange(size){
				this.pageSize = size
				this.getDiscussList(1);
			},
			// 返回按钮
			backClick(){
				let params = {}
				if(this.centerType){
					params.centerType = 1
				}
				this.$router.push({path: '/index/piaowuxinxi', query: params});
			},
			// 下载
			download(file ){
				if(!file) {
					this.$message({
						type: 'error',
						message: '文件不存在',
						duration: 1500,
					});
					return;
				}
				let arr = file.replace(new RegExp('upload/', "g"), "")
				axios.get(this.baseUrl + '/file/download?fileName=' + arr, {
					headers: {
						token: localStorage.getItem("frontToken")
					},
					responseType: "blob"
				}).then(({
					data
				}) => {
					const binaryData = [];
					binaryData.push(data);
					const objectUrl = window.URL.createObjectURL(new Blob(binaryData, {
						type: 'application/pdf;chartset=UTF-8'
					}))
					const a = document.createElement('a')
					a.href = objectUrl
					a.download = arr
					// a.click()
					// 下面这个写法兼容火狐
					a.dispatchEvent(new MouseEvent('click', {
						bubbles: true,
						cancelable: true,
						view: window
					}))
					window.URL.revokeObjectURL(data)
				},err=>{
					axios.get((location.href.split(this.$config.name).length>1 ? location.href.split(this.$config.name)[0] :'') + this.$config.name + '/file/download?fileName=' + arr, {
						headers: {
							token: localStorage.getItem("frontToken")
						},
						responseType: "blob"
					}).then(({
						data
					}) => {
						const binaryData = [];
						binaryData.push(data);
						const objectUrl = window.URL.createObjectURL(new Blob(binaryData, {
							type: 'application/pdf;chartset=UTF-8'
						}))
						const a = document.createElement('a')
						a.href = objectUrl
						a.download = arr
						// a.click()
						// 下面这个写法兼容火狐
						a.dispatchEvent(new MouseEvent('click', {
							bubbles: true,
							cancelable: true,
							view: window
						}))
						window.URL.revokeObjectURL(data)
					})
				})
			},
			//立即购买
			buyNow() {
				// 单次购买限制
				if (this.detail.onelimittimes > 0 && this.detail.onelimittimes < this.buynumber) {
					this.$message.error(`每人单次只能购买${this.detail.onelimittimes}件`);
					return
				}
				// 库存不够
				if (this.detail.alllimittimes <= 0 ) {
					this.$message.error(`商品已售罄`);
					return
				}
				// 库存限制
				if (this.detail.alllimittimes > 0 && this.detail.alllimittimes < this.buynumber) {
					this.$message.error(`库存不足`);
					return
				}
				// 保存到storage中，在确认下单页面中获取要购买的商品
				localStorage.setItem('orderGoods', JSON.stringify([{
					tablename: this.tablename,
					goodid: this.detail.id,
					goodname: this.title,

					picture:this.detailBanner[0],
					buynumber: this.buynumber,
					userid: localStorage.getItem('frontUserid'),
					price: this.detail.price,
					discountprice: this.detail.vipprice ? this.detail.vipprice : 0
				}]));
				// 跳转到确认下单页面
				let query = {
					type: 1,
				}
				this.$router.push({path: '/index/shop-order/orderConfirm', query: query});
			},


			// 权限判断
			btnAuth(tableName,key){
				if(this.centerType){
					return this.isBackAuth(tableName,key)
				}else{
					return this.isAuth(tableName,key)
				}
			},
			// 修改
			editClick(){
				this.$router.push(`/index/piaowuxinxiAdd?type=edit&&id=${this.detail.id}`);
			},
			// 删除
			async delClick(){
				await this.$confirm('是否删除此票务信息？') .then(_ => {
					this.$http.post('piaowuxinxi/delete', [this.detail.id]).then(async res => {
						if (res.data.code == 0) {
							this.$http.get('storeup/list',{params:{
								page: 1,
								limit: 100,
								refid: this.detail.id,
								tablename: 'piaowuxinxi',
							}}).then(async obj=>{
								if(obj.data&&obj.data.code==0){
									let arr = []
									for(let x in obj.data.data.list){
										arr.push(obj.data.data.list[x].id)
									}
									if(arr.length){
										await this.$http.post('storeup/delete',arr).then(()=>{})
									}
									this.$message({
										type: 'success',
										message: '删除成功!',
										duration: 1500,
										onClose: () => {
											history.back()
										}
									});
								}
							})
						}
					});
				}).catch(_ => {});
			},
		},
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.detail-preview {
		padding: 20px 0;
		margin: 0px auto;
		color: #666;
		display: flex;
		width: 1200px;
		font-size: 16px;
		justify-content: flex-start;
		align-items: flex-start;
		position: relative;
		flex-wrap: wrap;
		.attr {
			padding: 0;
			background: none;
			flex: 1;
			display: block;
			width: 100%;
			position: relative;
			.info {
				padding: 0;
				margin: 0;
				background: none;
				width: 100%;
				.title-item {
					border: 1px solid #ddd;
					padding: 0 10px;
					margin: 0 auto 3px;
					background: #fff;
					display: flex;
					border-color: #eee #f7db61;
					line-height: 40px;
					justify-content: space-between;
					align-items: center;
					.detail-title {
						color: #333;
						font-weight: 600;
						font-size: 18px;
					}
					.colectBtn {
						cursor: pointer;
						padding: 0px;
						background: none;
						.icon {
							color: #666;
							font-size: inherit;
						}
						.text {
							color: #666;
							font-size: inherit;
						}
					}
					.colectBtnActive {
						.icon {
							color: #df8230;
						}
						.text {
							color: #df8230;
						}
					}
					.colectBtn:hover {
						.icon {
							color: #df8230;
						}
						.text {
							color: #df8230;
						}
					}
					.colectBtnActive:hover {
						.icon {
							color: #df8230;
						}
						.text {
							color: #df8230;
						}
					}
				}
				.item {
					border: 1px solid #ddd;
					padding: 0 10px;
					margin: 0 auto 3px;
					background: #fff;
					display: flex;
					border-color: #eee #f7db61;
					line-height: 40px;
					justify-content: space-between;
					align-items: center;
					.lable {
						padding: 0 20px;
						color: #333;
						white-space: nowrap;
						font-weight: 500;
						width: auto;
						font-size: inherit;
						line-height: 40px;
						text-align: right;
						height: 40px;
					}
					.text {
						padding: 8px 10px 0;
						color: inherit;
						word-break: break-all;
						flex: 1;
						font-size: inherit;
						line-height: 24px;
						height: auto;
					}
					.price {
						color: #f00;
					}
					.bold {
						font-weight: bold;
					}
					.link {
						cursor: pointer;
						text-decoration: underline;
					}
				}
				.btn_box {
					padding: 10px 0;
					display: flex;
					flex-wrap: wrap;
				}
				.reserveBtn {
					border: 0;
					cursor: pointer;
					border-radius: 4px;
					padding: 0 10px;
					margin: 0 5px 0 0;
					outline: none;
					color: #000;
					background: rgba(64, 158, 255, .2);
					width: auto;
					font-size: inherit;
					line-height: 40px;
					height: 40px;
				}
				.reserveBtn:hover {
					background: rgba(64, 158, 255, 0.3);
				}
				.editBtn {
					border: 0;
					cursor: pointer;
					border-radius: 4px;
					padding: 0 10px;
					margin: 0 5px 0 0;
					outline: none;
					color: #000;
					background: rgba(64, 158, 255, .2);
					width: auto;
					font-size: inherit;
					line-height: 40px;
					height: 40px;
				}
				.editBtn:hover {
					background: rgba(64, 158, 255, 0.3);
				}
				.delBtn {
					border: 0;
					cursor: pointer;
					border-radius: 4px;
					padding: 0 10px;
					margin: 0 5px 0 0;
					outline: none;
					color: #000;
					background: rgba(255, 0, 0, .1);
					width: auto;
					font-size: inherit;
					line-height: 40px;
					height: 40px;
				}
				.delBtn:hover {
					background: rgba(255, 0, 0, .2);
				}
			}
		}
		.el-carousel {
			margin: 0 40px 0 0;
			width: calc(50% - 40px);
			height: 480px;
			order: -2;
			/deep/ .el-carousel__indicator button {
				width: 0;
				height: 0;
				display: none;
			}
			/deep/ .el-carousel__container {
				.el-carousel__arrow--left {
					width: 36px;
					font-size: 12px;
					height: 36px;
				}
				.el-carousel__arrow--left:hover {
					background: red;
				}
				.el-carousel__arrow--right {
					width: 36px;
					font-size: 12px;
					height: 36px;
				}
				.el-carousel__arrow--right:hover {
					background: red;
				}
				.el-carousel-item {
					border-radius: 10px;
					width: 100%;
					height: 100%;
					img {
						object-fit: cover;
						width: 100%;
						height: 100%;
					}
				}
			}
		
			/deep/ .el-carousel__indicators {
				padding: 0;
				margin: 0;
				z-index: 2;
				position: absolute;
				list-style: none;
				li {
					padding: 0;
					margin: 0 4px;
					background: #fff;
					display: inline-block;
					width: 12px;
					opacity: 0.4;
					transition: 0.3s;
					height: 12px;
				}
				li:hover {
					padding: 0;
					margin: 0 4px;
					background: #fff;
					display: inline-block;
					width: 24px;
					opacity: 0.7;
					height: 12px;
				}
				li.is-active {
					padding: 0;
					margin: 0 4px;
					background: #fff;
					display: inline-block;
					width: 24px;
					opacity: 1;
					height: 12px;
				}
			}
		}
		.detail-tabs {
			border: 0px solid #ccc;
			box-shadow: none;
			margin: 20px auto;
			background: #FFF;
			width: 100%;
			& /deep/ .el-tabs__header .el-tabs__nav-wrap {
				margin-bottom: 0;
			}
			/deep/ .el-tabs__header {
				padding: 5px 5px 0;
				margin: 0;
				background: #f7db61;
				border-color: #1f292f;
				border-width: 0 0 1px 0;
				border-style: solid;
			}
			
			/deep/ .el-tabs__header .el-tabs__item {
				border: 0;
				padding: 0 20px;
				margin: 0 5px;
				color: #333;
				background: transparent;
				font-weight: 500;
				display: inline-block;
				font-size: inherit;
				line-height: auto;
				position: relative;
				list-style: none;
				height: auto;
			}
			
			/deep/ .el-tabs__header .el-tabs__item:hover {
				border: 0;
				border-radius: 20px 20px 0 0;
				color: #333;
				background: #fff;
			}
			
			/deep/ .el-tabs__header .el-tabs__item.is-active {
				border: 0;
				border-radius: 20px 20px 0 0;
				margin: 0 5px;
				color: #333;
				background: #fff;
				font-size: inherit;
			}
			
			/deep/ .el-tabs__content {
				padding: 15px;
			}
		}
	}
    .seat-list{
        display: flex;
        align-items: center;
        flex-wrap: wrap;
        .seat-item{
            width: 20%;
            display: flex;
            flex-direction: column;
            align-items: center;
			img {
				width: 60px;
			}
            span{
                line-height: 3;
            }
        }
    }
	.share_view{
		box-shadow: 0 1px 6px rgba(0,0,0,.3);
		z-index: 11;
		bottom: 20%;
		background: #fff;
		position: fixed;
		right: 0;
		.share:last-of-type{
			border:none;
		}
	}
</style>
