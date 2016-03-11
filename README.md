# AndroidSuperDialog
IOS style Dialog
#效果图
<img src="preview/superDialog_01.png" width="240px"/>

<img src="preview/superDialog_02.png" width="240px"/>

<img src="preview/superDialog_03.png" width="240px"/>
#使用
    简单的对话框
                new SuperDialog.Builder(this).setTitle("标题").setMessage("内容从这里开始了")
                    .setNegativeButton("取消", null)
                    .setPositiveButton("确定", new SuperDialog.OnClickPositiveListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(v.getContext(), "点了确定", Toast.LENGTH_LONG).show();
                        }
                    }).build();
    选择对话框
                final String[] strings = {"拍照", "从相册选择", "小视频"};
                new SuperDialog.Builder(this).setGravity(Gravity.BOTTOM)
                        .setItems(strings, new SuperDialog.OnItemClickListener() {
                            @Override
                            public void onItemClick(int position) {
                                Toast.makeText(MainActivity.this, strings[position], Toast.LENGTH_LONG).show();
                            }
                        }).setNegativeButton("取消", null)
                        .build();
#说明
	此库自动将px转换百分比，由于 Dialog 布局一般只有微调，暂时只支持
	* textSize，height，padding。
	默认字体大小;Title、message、button、padding 的px在设计稿为 1080 * 1920 的尺寸
	所以使用时设计稿尺寸一定是1080 * 1920
#感谢
[AutoLayout-Android](https://github.com/DTHeaven/AutoLayout-Android)
