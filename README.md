# ShadowLayout

![](https://raw.githubusercontent.com/fbsum/ShadowLayout/master/art/shadowlayout.png)

## Dependencies

Step 1. Add the JitPack repository to your build file:

```groovy
    allprojects {
        epositories {
            maven { url 'https://jitpack.io' }
        }
    }
```

Step 2. Add the dependency:

```groovy
    dependencies {
            compile 'com.github.fbsum:shadowlayout:1.0.0'
    }
```

## Implement

```java
	private void init() {
		// ...
        shadowPaint = new Paint();
        shadowPaint.setColor(Color.WHITE);
        shadowPaint.setStyle(Paint.Style.FILL);
        shadowPaint.setAntiAlias(true);
        shadowPaint.setShadowLayer(shadowRadius, 0, 0, shadowColor);

        setLayerType(LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        if (shadowRect == null) {
            shadowRect = new RectF();
            shadowRect.left = getPaddingLeft() + shadowLeftOffset;
            shadowRect.top = getPaddingTop() + shadowTopOffset;
            shadowRect.right = getWidth() - getPaddingRight() - shadowRightOffset;
            shadowRect.bottom = getHeight() - getPaddingBottom() - shadowBottomOffset;
        }
        canvas.drawRoundRect(shadowRect, shadowCorner, shadowCorner, shadowPaint);
        super.dispatchDraw(canvas);
    }
```
## [Debug APK](https://raw.githubusercontent.com/fbsum/ShadowLayout/master/art/shadowlayout_v1.0.0.apk)
