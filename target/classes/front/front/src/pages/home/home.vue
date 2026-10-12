<template>
	<div class="home-preview">




		<!-- 系统简介 -->
		<div id="system" class="animate__animated">
			<div class="system_item">
				<div class="system_title_box">
					<div class="system_title">{{systemIntroductionDetail.title}}</div>
					<div class="system_subtitle">{{systemIntroductionDetail.subtitle}}</div>
				</div>
				<div class="system_img">
					<img :src="baseUrl + systemIntroductionDetail.picture1">
					<img :src="baseUrl + systemIntroductionDetail.picture2">
					<img :src="baseUrl + systemIntroductionDetail.picture3">
				</div>
				<div class="system_content" v-html="systemIntroductionDetail.content"></div>
				<div class="system_idea1" />
				<div class="system_idea2" />
				<div class="system_idea3" />
				<div class="system_idea4" />
				<div class="system_more" @click="toDetail('systemintroDetail',systemIntroductionDetail)">
					<span>查看更多</span>
					<span class="icon iconfont icon-gengduo1"></span>
				</div>
			</div>
		</div>
		<!-- 系统简介 -->
		<!-- 新闻资讯 -->
		<div id="animate_newsnews" class="news animate__animated">
			<div class="news_title_box">
				<span class="news_title">公告信息</span>
				<span class="news_subhead">{{'news'.toUpperCase()}}</span>
			</div>
			<div class="list list11 index-pv1">
				<div class="left_box">
					<div class="list-item" v-for="(item,index) in newsList" :key="index" v-if="index<Number(2)" @click="toDetail('newsDetail', item)">
						<div class="img"><img :src="baseUrl + item.picture" alt=""></div>
						<div class="infoBox">
							<div class="name">{{item.title}}</div>
							<div class="time">{{item.addtime}}</div>
							<div class="desc">{{ item.introduction }}</div>
						</div>
					</div>
				</div>
				<div class="right_box">
					<div class="list-item" :class="newsIndex11==index?'list-item-active':''" @click="newsTabClick11(index)"  v-for="(item,index) in newsList" :key="index" v-if="index>=Number(2)&&index<(Number(2) + Number(4))">
						<div class="name">{{item.title}}</div>
						<div class="list-item2" @click="toDetail('newsDetail', item)">
							<div class="desc">{{ item.introduction }}</div>
						</div>
					</div>
				</div>
			</div>
			<div class="moreBtn" @click="moreBtn('news')">
				<span class="text">查看更多</span>
				<i class="icon iconfont icon-gengduo1"></i>
			</div>
		</div>
		<!-- 新闻资讯 -->
		<!-- 特价商品 -->
		<div id="animate_listpiaowuxinxi" class="lists animate__animated">
			<div class="list_title_box">
				<span class="list_title">票务信息展示</span>
				<span class="list_subhead">{{'piaowuxinxi'.toUpperCase()}} SHOW</span>
			</div>
			<div class="list list21 index-pv1">
				<div class="list-body-top">
					<div class="tab-title">票务信息推荐</div>
					<div class="tab_view">
						<div class="tab" :class="listIndex21piaowuxinxi == 0 ? 'tabActive' : ''" @click="listIndexClick21(0, 'piaowuxinxi')">
							全部
						</div>
						<template v-for="(item,index) in listList21piaowuxinxi">
							<div v-if="index < 6" class="tab" :class="listIndex21piaowuxinxi == index + 1 ? 'tabActive' : ''" @click="listIndexClick21(index + 1, 'piaowuxinxi')">
								{{item[listColumn21piaowuxinxi]}}
							</div>
						</template>
					</div>
					<div class="tab-right" @click="moreBtn('piaowuxinxi')">
						<span class="text">查看更多</span>
						<span class="icon iconfont icon-gengduo1"></span>
					</div>
				</div>
				<div class="list-body">
					<div v-if="index<Number(4)" class="list-item" v-for="(item,index) in piaowuxinxiList" :key="index" @click="toDetail('piaowuxinxiDetail', item)">
						<div class="img">
							<img v-if="preHttp(item.tupian)" :src="item.tupian.split(',')[0]" alt="" />
							<img v-else :src="baseUrl + (item.tupian?item.tupian.split(',')[0]:'')" alt="" />
						</div>
						<div class="infoBox">
							<div class="name">{{item.piaowumingcheng}}</div>
							<div class="name">{{item.piaowuleixing}}</div>
							<div class="name">{{item.yanchuriqi}}</div>
							<div class="name">演出时间:{{item.yanchushijian}}</div>
							<div class="bottomInfo">
								<div class="price">￥{{item.price}}</div>
								<div class="time_item">
									<span class="icon iconfont icon-shijian21"></span>
									<span class="label">发布时间：</span>
									<span class="text">{{item.addtime.split(' ')[0]}}</span>
								</div>
								<div class="collect_item">
									<span class="icon iconfont icon-shoucang10"></span>
									<span class="label">收藏：</span>
									<span class="text">{{item.storeupnum}}</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="moreBtn" @click="moreBtn('piaowuxinxi')">
				<span class="text">查看更多</span>
				<i class="icon iconfont icon-gengduo1"></i>
			</div>
	

		</div>
		<!-- 特价商品 -->
	</div>
</template>

<script>
import 'animate.css'
import Swiper from "swiper";

	export default {
		//数据集合
		data() {
			return {
				baseUrl: '',
				systemIntroductionDetail: {},
				newsList: [],

				piaowuxinxiList: [],

				newsIndex11: Number(2),


				listIndex21piaowuxinxi: 0,
				listList21piaowuxinxi: [],
				listColumn21piaowuxinxi: '',

			}
		},
		created() {
			this.baseUrl = this.$config.baseUrl;
			this.getNewsList();
			this.getSystemIntroduction();
			this.getList();
			this.piaowuxinxiListCate()
		},
		mounted() {
			window.addEventListener('scroll', this.handleScroll)
			setTimeout(()=>{
				this.handleScroll()
			},100)
			
			this.swiperChanges()
		},
		beforeDestroy() {
			window.removeEventListener('scroll', this.handleScroll)
		},
		//方法集合
		methods: {
			newsTabClick11(index){
				this.newsIndex11 = index
			},
			swiperChanges() {
				setTimeout(()=>{
				},750)
			},
			async recommendIndexClick18(index, name) {
				this['recommendIndex18' + name] = index
				await this.getList()
			},

			piaowuxinxiListCate(){
				this.$http.get('piaowuleixing/list',{}).then(res => {
					if (res.data.code == 0) {
						this.listList21piaowuxinxi = res.data.data.list
					}
				});
				this.listColumn21piaowuxinxi = 'piaowuleixing'
			},
			listIndexClick21(index, name) {
				this['listIndex21' + name] = index[this['listColumn21' + name]]
				this.getList()
			},

			handleScroll() {
				let arr = [
					{id:'about',css:'animate__fadeInUp'},
					{id:'system',css:'animate__fadeInUp'},
					{id:'animate_listpiaowuxinxi',css:'animate__fadeInUp'},
					{id:'animate_newsnews',css:'animate__fadeInUp'},
				]
			
				for (let i in arr) {
					let doc = document.getElementById(arr[i].id)
					if (doc) {
						let top = doc.offsetTop
						let win_top = window.innerHeight + window.pageYOffset
						// console.log(top,win_top)
						if (win_top > top && doc.classList.value.indexOf(arr[i].css) < 0) {
							// console.log(doc)
							doc.classList.add(arr[i].css)
						}
					}
				}
			},
			preHttp(str) {
				return str && str.substr(0,4)=='http';
			},
			getSystemIntroduction() {
				this.$http.get('systemintro/detail/1', {}).then(res => {
					if(res.data.code == 0) {
						this.systemIntroductionDetail = res.data.data;
					}
				})
			},
			getNewsList() {
				let data = {
					page: 1,
					limit: 7,
					sort: 'addtime',
					order: 'desc'
				}
				this.$http.get('news/list', {params: data}).then(res => {
					if (res.data.code == 0) {
						this.newsList = res.data.data.list;
					
					}
				});
			},
			getList() {
				let autoSortUrl = "";
				let data = {}
			
				data = {
					page: 1,
					limit: 8,
				}
				this.$http.get('piaowuxinxi/list', {params: data}).then(res => {
					if (res.data.code == 0) {
						this.piaowuxinxiList = res.data.data.list;
					}
				});
			},
			toDetail(path, item) {
				this.$router.push({path: '/index/' + path, query: {id: item.id}});
			},
			moreBtn(path) {
				this.$router.push({path: '/index/' + path});
			}
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.home-preview {
		margin: 0px auto;
		flex-direction: column;
		background: #fff;
		display: flex;
		width: 100%;
		#system {
			padding: 20px 0 60px;
			background: #fff;
			width: 100%;
			height: auto;
			order: 5;
			.system_item {
				margin: 0 auto;
				display: block;
				width: 1200px;
				flex-wrap: wrap;
				.system_title_box {
					margin: 30px auto;
					width: 100%;
					line-height: 54px;
					text-align: center;
					.system_title {
						margin: 0 20px 0 0;
						color: #333;
						display: inline-block;
						font-size: 40px;
					}
					.system_subtitle {
						color: #999;
						display: inline-block;
						font-size: 24px;
					}
				}
				.system_img {
					padding: 0px;
					background: none;
					display: flex;
					width: 60%;
					float: right;
					flex-wrap: wrap;
					height: 420px;
					img:nth-child(1) {
						margin: 0 20px 0 0;
						object-fit: cover;
						display: block;
						width: calc(40% - 20px);
						height: 100%;
					}
					img:nth-child(2) {
						object-fit: cover;
						display: block;
						width: 60%;
						height: 100%;
					}
					img:nth-child(3) {
						margin: 0;
						flex: 1;
						object-fit: cover;
						display: none;
						height: 100%;
					}
				}
				.system_content {
					border: 4px double #f7db61;
					padding: 15px 10px 5px 20px;
					margin: 0 20px 10px 0;
					overflow: hidden;
					color: #333;
					background: #fff;
					width: calc(40% - 20px);
					font-size: 16px;
					line-height: 30px;
					float: left;
					height: 360px;
				}
				.system_idea1 {
					background: url(http://codegen.caihongy.cn/20201114/7856ba26477849ea828f481fa2773a95.jpg) 0% 0% / cover no-repeat;
					display: none;
					width: 285px;
					height: 100px;
				}
				.system_idea2 {
					background: url(http://codegen.caihongy.cn/20201114/7856ba26477849ea828f481fa2773a95.jpg) 0% 0% / cover no-repeat;
					display: none;
					width: 285px;
					height: 100px;
				}
				.system_idea3 {
					background: url(http://codegen.caihongy.cn/20201114/7856ba26477849ea828f481fa2773a95.jpg) 0% 0% / cover no-repeat;
					display: none;
					width: 285px;
					height: 100px;
				}
				.system_idea4 {
					background: url(http://codegen.caihongy.cn/20201114/7856ba26477849ea828f481fa2773a95.jpg) 0% 0% / cover no-repeat;
					display: none;
					width: 285px;
					height: 100px;
				}
				.system_more {
					border: 0;
					padding: 0 15px;
					margin: 20px auto 0;
					background: #f7db61;
					display: block;
					width: auto;
					line-height: 32px;
					float: left;
					text-align: center;
					span:nth-child(1) {
						color: #333;
						font-size: 15px;
					}
					span:nth-child(2) {
						color: #333;
						font-size: 15px;
					}
				}
				.system_more:hover {
					cursor: pointer;
					opacity: 1;
				}
			}
		}
		.news {
			margin: 20px 0 0;
			background: #fff;
			width: 100%;
			order: 2;
			.news_title_box {
				margin: 30px auto;
				background: none;
				width: 100%;
				line-height: 54px;
				text-align: center;
				.news_title {
					margin: 0 20px 0 0;
					color: #333;
					font-size: 40px;
				}
				.news_subhead {
					margin: 0 0 10px;
					color: #999;
					width: 100%;
					font-size: 20px;
					line-height: 1.5;
					text-align: center;
				}
			}
			.index-pv1 .animation-box:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
			}
			.index-pv1 .animation-box img:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
			.list11 {
				padding: 20px;
				margin: 50px auto;
				overflow: hidden;
				background: none;
				display: flex;
				width: 1200px;
				justify-content: space-between;
				.left_box {
					width: 48%;
					.list-item {
						border: 1px dashed #bbbbbb;
						cursor: pointer;
						padding: 0 0 25px;
						margin: 0 0 24px;
						overflow: hidden;
						display: flex;
						width: 100%;
						border-width: 0 0 1px;
						justify-content: space-between;
						.img {
							width: 185px;
							height: 150px;
							img {
								object-fit: cover;
								width: 100%;
								transition: all 0.3s;
								height: 100%;
							}
						}
						.infoBox {
							width: calc(100% - 205px);
							.name {
								overflow: hidden;
								color: #666;
								white-space: nowrap;
								font-weight: 600;
								width: 100%;
								font-size: 16px;
								line-height: 26px;
								text-overflow: ellipsis;
							}
							.time {
								margin: 10px 0;
								color: #6a6a6a;
								font-size: 14px;
							}
							.desc {
								overflow: hidden;
								color: #858585;
								line-height: 24px;
								height: 72px;
							}
						}
					}
					.list-item:hover {
						.img {
							img {
								transform: scale(1.05);
							}
						}
						.infoBox {
							.name {
								color: #e3c026;
							}
						}
					}
				}
				.right_box {
					padding: 0 0 0 30px;
					overflow: hidden;
					width: 48%;
					height: 418px;
					.list-item {
						position: relative;
						.name {
							cursor: pointer;
							padding: 0 50px 0 25px;
							overflow: hidden;
							color: #353535;
							white-space: nowrap;
							font-weight: normal;
							width: 100%;
							font-size: 15px;
							line-height: 40px;
							position: relative;
							text-overflow: ellipsis;
						}
						.name::before {
							color:  #666;
							top: 0;
							font-weight: normal;
							font-size: 22px;
							line-height: 40px;
							position: absolute;
							right: 25px;
							content: "+";
						}
						.list-item2 {
							border: 1px dashed #bbbbbb;
							padding: 0 25px;
							margin: 0;
							overflow: hidden;
							display: block;
							border-width: 0 0 1px;
							opacity: 0;
							transition: all 0.5s;
							height: 0;
							.desc {
								cursor: pointer;
								margin: 10px 0 0;
								overflow: hidden;
								color: #909090;
								font-size: 14px;
								line-height: 25px;
								height: 200px;
							}
						}
					}
					.list-item.list-item-active {
						.name {
							color: #333;
							background: #f7db61;
						}
						.name::before {
							color: #fff;
							content: "-";
						}
						.list-item2 {
							padding: 0 25px 16px;
							margin: 0 0 10px;
							display: block;
							opacity: 1;
							transition: all 0.5s;
							height: 174px;
						}
					}
				}
			}
			.moreBtn {
				border: 0;
				cursor: pointer;
				margin: 20px auto 40px;
				background: #f7db61;
				display: block;
				width: 110px;
				line-height: 32px;
				text-align: center;
				.text {
					color: #000;
					font-size: 15px;
				}
				.icon {
					color: #000;
					font-size: 15px;
				}
			}
		}
		.lists {
			padding: 0;
			margin: 0;
			background: #f6f6f6;
			width: 100%;
			order: 4;
			.list_title_box {
				margin: 50px auto 30px;
				background: none;
				width: 100%;
				line-height: 54px;
				text-align: center;
				.list_title {
					margin: 0 20px 0 0;
					color: #000;
					font-size: 40px;
				}
				.list_subhead {
					margin: 0 0 10px;
					color: #999;
					width: 100%;
					font-size: 20px;
					line-height: 1.5;
					text-align: center;
				}
			}
			.index-pv1 .animation-box {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				z-index: initial;
			}
			
			.index-pv1 .animation-box:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, -10px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
			}
			
			.index-pv1 .animation-box img {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			}
			
			.index-pv1 .animation-box img:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
			.list21 {
				padding: 0;
				color: #888;
				background: none;
				width: 100%;
				font-size: 14px;
				.list-body-top {
					padding: 60px calc((100% - 1200px)/2) 180px;
					margin: 0 auto;
					background: url(http://codegen.caihongy.cn/20240809/ce61590e38bc47b0b2b556bdeaa52d38.jpg) no-repeat center bottom / cover;
					display: flex;
					width: 100%;
					align-item: center;
					.tab-title {
						margin: 0 40px 0 0;
						color: #333;
						display: none;
						font-size: 28px;
						line-height: 34px;
						height: 38px;
					}
					.tab_view {
						padding: 0 20px;
						display: flex;
						width: 100%;
						.tab {
							cursor: pointer;
							padding: 0 30px;
							margin: 2px 0 0;
							color: #333;
							font-size: 16px;
							line-height: 34px;
							height: 34px;
						}
						.tab::before {
							margin: 0 0 0 -6px;
							left: 50%;
							bottom: -6px;
							display: inline-block;
							position: absolute;
							content: "";
						}
						.tab:hover {
							color: #000;
							background: #fff;
							position: relative;
						}
						.tab:hover::before {
							border: 1px solid #fff;
							margin: 0 0 0 -6px;
							left: 50%;
							bottom: -6px;
							display: inline-block;
							border-color: #fff transparent;
							border-width: 6px 6px 0;
							position: absolute;
							content: "";
						}
						.tab.tabActive:hover {
							color: #000;
							background: #fff;
							position: relative;
						}
						.tab.tabActive:hover::before {
							border: 1px solid #fff;
							margin: 0 0 0 -6px;
							left: 50%;
							bottom: -6px;
							display: inline-block;
							border-color: #fff transparent;
							border-width: 6px 6px 0;
							position: absolute;
							content: "";
						}
					}
					.tab-right {
						cursor: pointer;
						display: none;
						line-height: 38px;
						height: 38px;
						.text {
							color: #fff;
							font-size: 16px;
						}
						.icon {
							color: #f5f5f5;
							font-size: 12px;
						}
					}
				}
				.list-body {
					padding: 0 20px;
					margin: -150px auto 0;
					display: flex;
					width: 1200px;
					justify-content: space-between;
					.list-item {
						padding: 0 0 60px;
						margin: 0;
						background: #fff;
						width: 23.5%;
						position: relative;
						.img {
							width: 100%;
							height: 300px;
							img {
								object-fit: cover;
								width: 100%;
								height: 100%;
							}
						}
						.infoBox {
							border: 1px solid #ddd;
							padding: 20px;
							left: 0;
							background: #fff;
							bottom: 0;
							border-width: 0 1px 1px;
							position: absolute;
							right: 0;
							.name {
								cursor: pointer;
								margin: 0 0 0px;
								overflow: hidden;
								color: #333;
								white-space: nowrap;
								font-weight: 600;
								display: block;
								width: 100%;
								font-size: 16px;
								line-height: 28px;
								text-overflow: ellipsis;
								text-align: center;
							}
							.bottomInfo {
								overflow: hidden;
								transition: all 0.6s;
								height: 0;
								.price {
									margin: 10px auto 4px;
									color: #f00;
									width: 100%;
									font-size: 18px;
									line-height: 24px;
								}
								.time_item {
									padding: 0;
									.icon {
										margin: 0 2px 0 0;
										line-height: 1.5;
									}
									.label {
										line-height: 1.5;
									}
									.text {
										line-height: 1.5;
									}
								}
								.publisher_item {
									padding: 0;
									.icon {
										margin: 0 2px 0 0;
										line-height: 1.5;
									}
									.label {
										line-height: 1.5;
									}
									.text {
										line-height: 1.5;
									}
								}
								.like_item {
									padding: 0;
									.icon {
										margin: 0 2px 0 0;
										line-height: 1.5;
									}
									.label {
										line-height: 1.5;
									}
									.text {
										line-height: 1.5;
									}
								}
								.collect_item {
									padding: 0;
									.icon {
										margin: 0 2px 0 0;
										line-height: 1.5;
									}
									.label {
										line-height: 1.5;
									}
									.text {
										line-height: 1.5;
									}
								}
								.view_item {
									padding: 0;
									.icon {
										margin: 0 2px 0 0;
										line-height: 1.5;
									}
									.label {
										line-height: 1.5;
									}
									.text {
										line-height: 1.5;
									}
								}
							}
						}
					}
					.list-item::after {
						bottom: 0;
						background: #ffcb2d;
						width: 0;
						position: absolute;
						content: "";
						transition: all 0.6s;
						height: 5px;
					}
					.list-item:hover {
						box-shadow: 0 5px 20px rgba(0, 0, 0, 0.1);
						.infoBox {
							.bottomInfo {
								cursor: pointer;
								height: 200px;
							}
						}
					}
					.list-item:hover::after {
						width: 100%;
					}
				}
			}
			.moreBtn {
				border: 0;
				cursor: pointer;
				margin: 30px auto 50px;
				background: #f7db61;
				display: block;
				width: 110px;
				line-height: 32px;
				text-align: center;
				.text {
					color: #333;
					font-size: 15px;
				}
				.icon {
					color: #333;
					font-size: 15px;
				}
			}
		}
	}
</style>
