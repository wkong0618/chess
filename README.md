使用Java实现一个简单的五子棋AI。

## 假设信息
- 假设棋盘大小为 20x20
- 每局棋开始都是由用户先落子
- 规则：某一方的棋子先在横、竖、或斜的方向上有五个棋子连成一线，则胜。如果棋盘所有位置都占满也没有达到，则为和棋。
- 落子位置表示为`{x:number,y:number}`，以左下角为原点，从`0`开始算起

## 实现

会提供以下rest api接口：

### 1. 开启新的一局游戏

```
POST /games
```

参数：无

返回值：
```
{ gameId: number }
```

### 2. 用户落子
```
POST /games/{gameId}/positions
```

#### 参数：
落子位置
```
{ x: number, y: number}
```
如果落子位置有误，则返回某种错误

#### 返回值
有四种情况：

1. 进行中
```
{ complete: false, aiNextPosition: {} }
```

2. 用户胜
```
{ complete: true, winner: 'user' }
```

3. AI胜
```
{ complete: true, winner: 'AI' }
```

4. 和局（无落子位置）
```
{ complete: true, winner: 'none' }
```

### 3. 棋局列表

```
GET /games
```

参数：无

返回值：所有棋局gameId，以及完成情况
```
{
  games: {
    id: number,
    complete: boolean,
    winner: string
  }
}
```

### 4. 某一局详情

```
GET /games/{gameId}
```

参数: 无

返回值：该棋局落子历史信息，以及完成情况
```
{
  gameId: number,
  positions: {from: 'user|AI', position: {x: number, y: number}}[],
  complete: boolean,
  winner: string
}
```

## AI策略

按顺序使用以下规则实现一个简单的AI：
1. 如果发现自己下子后可以获胜，则在对应位置落子
1. 否则，当发现用户的棋有四个连在一起的，需要去落子堵住一头
1. 否则，当发现用户的棋有三个连在一起的，需要去落子堵住一头
2. 否则，在合适位置落子尽量去构建一个最长的连子

## 实现内容

1. 简单启动和关闭脚本
2. 使用springboot框架，实现Restful接口
3. 使用slite数据库（关系数据库或NoSQL）

