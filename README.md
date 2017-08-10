文字を１文字ずつ表示させる View

<img src="https://gyazo.com/69a6f323ad08faa0bf6081fce51aa0a1.gif"/>

## 元ネタ
http://www.ore-memo.com/813.html
実装はこちらのサイトで紹介されている通り.
`CharByCharTextView` というカスタムビューにまとめた.

## 使い方
`CharByCharTextView` を使う.

`andrid.os.Handler` を使っている.

CharByCharTextView は
- startCharByCharAnim() を呼ぶとテキストアニメーションが開始される
- setTargetText(String target) で表示する文字列を設定できる
- 表示間隔は `INTERVAL = 2` となっている値を変えることで変更できる
