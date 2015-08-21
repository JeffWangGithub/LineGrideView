# LineGrideView 自定义带有网格的GrideView
带有网格线的GrideView，支持嵌套ScrollView

```xml
<ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center_horizontal"
            android:layout_marginTop="20dp"
            android:textSize="20sp"
            android:text="LineGrideViewSample" />

        <com.meilishuo.linegrideview.view.LineGridView
            android:id="@+id/line_grideview"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginTop="10dp"
            android:numColumns="3"
            app:showTopLine="true"
            app:showButtonLine="true"
            app:inScrollView="true" />
    </LinearLayout>
</ScrollView>
```
app:showTopLine="true"   ： 是否显示上边线 默认为true
app:showButtonLine="true"  ：是否显示下边线 默认为true
app:inScrollView="true"   ： 是否放在了可滚动的控件中 ，默认为fasle



