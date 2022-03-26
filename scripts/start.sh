## 只提供简单启动命令(当前可以作图模拟落子通过接口返回测试，后期补充可以图形化打印)

## 项目为maven项目，自己打包需要执行clean package。项目提交的时候我也打了一个jar包提供使用(放在scripts下)
## 通过下面的脚本启动以后，在mac或者linux我们提供一个执行例子：
#开启新游戏
#curl -H "Content-Type: application/json" -X POST  "http://127.0.0.1:9096/games"
#{"gameId":509845357467926528}
#
#棋局列表
#curl http://127.0.0.1:9096/games
#{"games":[{"id":509845357467926528,"complete":false,"winner":null}]}
#
#某一局详情
#curl http://127.0.0.1:9096/games/509845357467926528
#{"gameId":509845357467926528,"positions":[],"complete":false,"winner":null}
#
#用户落子（用户落子的返回外层包装了code和message(有相关校验),上面接口暂不封装）
#curl -H "Content-Type: application/json" -X POST -d '{"x":3,"y":3}'  "http://127.0.0.1:9096/games/509845357467926528/positions"
#{"code":200,"message":null,"data":{"complete":false,"winner":null,"aiNextPosition":{"x":2,"y":4}}}

nohup java  -server -jar chess-web.jar >/dev/null &

echo "base url:"
echo "开启新的一局游戏(post):http://127.0.0.1:9096/games"
echo "用户落子(post):http://127.0.0.1:9096/games/{gameId}/positions"
echo "棋局列表(get):http://127.0.0.1:9096/games"
echo "某一局详情(get):http://127.0.0.1:9096/games/{gameId}"

echo "提示:jdk版本为1.8,日志会生成在当前Jar同目录下(logs/common.log可以看到启动日志)"