export default {
	baseUrl: 'http://localhost:8080/springbooth56pqtxo/',
	name: '/springbooth56pqtxo',
	indexNav: [
		{
			name: '票务信息',
			url: '/index/piaowuxinxi',
		},
		{
			name: '公告信息',
			url: '/index/news'
		},
		{
			name: '留言反馈',
			url: '/index/messages'
		},
	],
	cateList: [
		{
			name: '票务信息',
			refTable: 'piaowuleixing',
			refColumn: 'piaowuleixing',
		},
	]
}
