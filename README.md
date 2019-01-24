# ShadowLayout

![](https://raw.githubusercontent.com/fbsum/ShadowLayout/master/art/shadowlayout.jpg)

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
            compile 'com.github.fbsum:shadowlayout:1.1.0'
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
        shadowPaint.setShadowLayer(shadowBlur, shadowOffsetDx, shadowOffsetDy, shadowPaintColor);

        setLayerType(LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        if (shadowRect == null) {
            shadowRect = new RectF();
            shadowRect.left = getPaddingLeft() + shadowOffsetLeft;
            shadowRect.top = getPaddingTop() + shadowOffsetTop;
            shadowRect.right = getWidth() - getPaddingRight() - shadowOffsetRight;
            shadowRect.bottom = getHeight() - getPaddingBottom() - shadowOffsetBottom;
        }
        canvas.drawRoundRect(shadowRect, shadowRound, shadowRound, shadowPaint);
        super.dispatchDraw(canvas);
    }
```
## [Debug APK](https://raw.githubusercontent.com/fbsum/ShadowLayout/master/art/shadowlayout_v1.1.0.apk)
