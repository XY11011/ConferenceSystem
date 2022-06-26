# ConferenceSystem
## 软件架构设计课程项目：会议管理系统
系统分为游客、普通用户、审稿人和会议发布者四个角色
### 普通用户：
- 登录注册：无账号的用户可以先注册账号再登录，已有账号的用户可通过用户名和密码登录进入系统
- 修改个人信息：已登录用户可以查看自己的个人信息，需要修改的话可以进行更新
- 修改密码：已登录用户可以修改登录密码
- 查看所有的会议列表：一进来可以看到所有会议列表（包括开始和截止投稿时间 会议按截止时间倒序，可进行关键字搜索会议）
- 查看会议的详细信息：点击会议，可以看到会议的详细信息。然后有投稿按钮（未登录用户告知权限不足，需要先登录）
- 投稿：普通用户可以向会议投稿，需要填写论文信息，上传论文PDF，最后提交
- 查看用户已投稿的会议列表：已登录用户可以查看自己所投稿的会议列表，点击进去，能查看到自己投的论文的状态、评审意见等
### 审稿人：
- 登录注册
- 修改个人信息
- 修改密码
- 查看所有分配到的论文列表：审稿人一进入系统就会看到会议发布者分配的论文列表（已审/未审）。
- 查看论文信息：审稿人可以查看论文的相关信息，并可以下载论文的pdf。若此论文未审，则可以填写审稿评级和意见；若已审，则显示已填写的意见，审稿人也可以修改评级和意见。
### 会议发布者：
- 登录注册
- 管理会议，包括分页查看会议列表、发布会议（填写所要发布的会议信息，比如说开始时间、截止时间、征稿信息等）、搜索会议（按会议标题来搜索会议）、修改会议、删除会议
- 管理审稿人：包括分页查看审稿人列表、新增审稿人、搜索审稿人（按审稿人的用户名来搜索审稿人）
- 管理稿件：包括分页查看稿件列表、搜索稿件（按会议名称或稿件标题来搜索稿件），以及可以给未分配稿件分配三个审稿人、决定已评审的稿件是否录用。