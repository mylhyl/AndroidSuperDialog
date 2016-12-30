# AndroidSuperDialog
   基于`DialogFragment`封装，支持自定义边框圆角、背景透明度、字体大小与色值等。
   列表选择框可以接收`List`与`Arrays`的数据源，详细见`demo`。
   初衷是掌握知识点，此库不一定适合你的产品整体风格，当然能够适合你的项目最好不过，有建议和不足之处欢迎骚扰。
# 知识点
  全代码创建`shape`、`selector`、`Layout`，三大`Layout`不用多讲，肯定都会的，主要是`Shape`所使用类如下：
  * `shape`对应`ShapeDrawable`、`RoundRectShape`
  * `selector`对应`StateListDrawable`

#效果图
<img src="preview/superDialog_01.png" width="240px"/>

<img src="preview/superDialog_02.png" width="240px"/>

<img src="preview/superDialog_03.png" width="240px"/>
# 引入

```xml
 compile 'com.mylhyl:superDialog:1.0.9'
```

eclipse 可以[点击这里下载aar文件](http://jcenter.bintray.com/com/mylhyl/superDialog/), 然后用zip解压取出jar包

#使用
简单的对话框

```java
                new SuperDialog.Builder(this).setRadius(10)
                        .setAlpha(0.5f)
                        .setTitle("标题").setMessage("可以看到？")
                        .setPositiveButton("确定", new SuperDialog.OnClickPositiveListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(v.getContext(), "点了确定", Toast.LENGTH_LONG).show();
                            }
                        }).build();
```

选择对话框

```java
                //final String[] strings = {"拍照", "从相册选择", "小视频"};
                List<People> list = new ArrayList<>();
                list.add(new People(1,"拍照"));
                list.add(new People(2,"从相册选择"));
                list.add(new People(3,"小视频"));
                new SuperDialog.Builder(this)
                        //.setAlpha(0.5f)
                        //.setGravity(Gravity.CENTER)
                        //.setTitle("上传头像", ColorRes.negativeButton)
                        .setCanceledOnTouchOutside(false)
                        .setItems(list, new SuperDialog.OnItemClickListener() {
                            @Override
                            public void onItemClick(int position) {
                                Toast.makeText(MainActivity.this, strings[position], Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton("取消", null)
                        .build();
```

* 宽度`setWidth(@FloatRange(from = 0.0, to = 1.0) float width)`
* 边距`setPadding(int left, int top, int right, int bottom)`
* 动画`setWindowAnimations(int animStyle)`
* 列表框距离下方按钮的间距`setItemsBottomMargin(int bottomMargin)`
* 指定位置显示`setShowAtLocation(int gravity, int x, int y)`
* 设置背景是否昏暗`setDimEnabled(boolean dimEnabled)`
* `setConfigDialog`更多使用
```java
	.setConfigDialog(new SuperDialog.ConfigDialog() {
		@Override
		public void onConfig(Dialog dialog, Window window, WindowManager
				.LayoutParams wlp, DisplayMetrics dm) {
			window.setWindowAnimations(R.style.dialogWindowAnim);//动画
			wlp.y = 100;//底部距离
		}
	})
```

#说明
	* 此库自动将px转换百分比，由于 Dialog 布局一般只有微调，暂时只支持textSize，height，padding
	* 默认字体大小;Title、message、button、padding 的px在设计稿为 1080 * 1920 的尺寸
	* 所以使用时设计稿尺寸一定是1080 * 1920
QQ交流群:435173211

#感谢
[AutoLayout-Android](https://github.com/DTHeaven/AutoLayout-Android)

# 版本

> 1.0.9 修复`setPadding`引起的crash

> 1.0.8 修复列表模式单条数据无圆角效果

> 1.0.7 修复旋转屏幕引起的crash

> 1.0.6 增加动画、边距等配置

> 1.0.5 支持调整宽度`setWidth`

> 1.0.4 修复无底部按钮，内容下边缘无圆角Bug

> 1.0.3 修复`dismiss()`无效的问题

> 1.0.2 增加背景方法`setBackgroundColor`
