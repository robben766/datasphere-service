
### 工作空间
--------------------------------

工作区存储DataSphere的分析实体，例如工作表，笔记本和工作台。 工作空间有两种类型：个人工作空间和共享工作空间。

* 个人工作区：分配给每个发现成员的私人工作区。 它只能由所有者访问。

* 共享工作空间：由多个用户共享的公共工作空间。 它用于用户彼此共享分析过程和结果。 共享工作区的所有者或管理员可以向DataSphere 成员授予各种级别的访问权限。


#### 工作空间主页

在工作空间主页上，您可以执行工作空间中包含的DataSphere实体（工作簿，笔记本和工作台）的管理。

##### 工作空间组成

工作空间所有组成如下：

* 工作区信息：显示工作区的名称和描述。 如果登录用户拥有该工作空间，则该工作空间名称旁边将显示一个所有者信息。

* 已注册实体：按实体类型显示在工作空间中注册的实体数。

* 数据源信息：显示工作空间中使用的数据源数量。单击此区域以显示这些数据源的列表。


#### 共享工作空间

共享的工作区旨在供多个用户访问和使用。 以下小节描述了如何查看和创建共享工作区，并解释了“权限模式”，它设置了允许哪些用户或群组访问共享工作区。

#### 创建共享空间

创建一个新的共享工作区，如下所示：

在共享工作区列表页面上，创建新的共享工作区。

输入名称和描述，然后通过参考以下描述来设置权限模式：

* 使用预设模式：加载管理员定义的权限模式。

* 使用自定义模式：定义新的权限模式。（有关如何定义新的权限模式，请参阅设置共享工作区的访问权限。）

单击"完成"，以完成创建工作区。

#### 为共享空间设置访问权限

通过以下两个步骤来设置共享工作区的访问权限：

* 为每个用户角色设置访问权限（请参阅设置权限模式）

* 向每个用户或用户组授予角色（请参阅设置共享成员和组）


#### 设置权限模式

#### 查看权限模式

单击“设置权限模式”以查看已定义的权限模式，如下所示：


在上面的示例中，将管理员，编辑员，查看员和访客定义为用户角色。 如本示例所示，权限模式是一组定义不同访问权限的用户角色。

每列确定的内容如下：

默认角色

添加新用户或用户组后，将为其分配默认角色。

每种实体类型（工作簿/笔记本/工作台）的权限。

* 查看：允许访问和查看该类型实体中的数据。
* 创建：允许创建，编辑和删除该类型的实体。
* 编辑：允许编辑或删除其他用户创建的类型的实体。

工作空间权限

* 创建文件夹：允许在工作区中创建，编辑和删除文件夹。

* 设置配置：允许修改工作空间的名称和描述以及更改工作空间权限模式。

#### 更改权限模式

单击权限模式视图页面上的“更改模式”按钮，以移动到用于更改定义的权限模式的页面，如下所示：


单击右侧的“选择角色集”组合框，以显示管理员定义的权限模式。 列表底部的自定义模式允许您设置新的用户角色。 选择一个以显示以下部分。 （如果选择“自定义模式”，则必须首先为每个用户角色定义一个权限。单击“新建模式”以移动到权限设置页面，并通过参考“查看权限模式”来设置每个用户角色的权限。）


在此，将当前权限模式的每个用户角色替换为新权限模式中定义的用户角色。 显示分配给该用户角色的权限。 单击“完成”以完成权限模式的设置。

#### 设置共享成员和群组

单击共享工作区主页右上角的图标，然后单击“设置共享成员和群组”以移动到用于设置共享工作区的成员和群组的页面，如下所示：在此页面上，为每个用户或用户组分配了一个权限模式中定义的用户角色。 请参考以下说明，分配用户角色，然后单击“完成”以完成工作区访问权限的设置。

* 用户角色：单击它以弹出一个对话框，显示权限架构，该模式定义了每个用户角色的权限。
* 成员/群组列表：列出在发现中注册的用户（对于组选项卡为组）。 单击列表中的用户（组）以将其添加到右侧的角色分配部分。 单击添加的用户（组）以将其从右侧部分中删除。
* 分配用户角色：单击此组合框以显示在活动权限模式中定义的用户角色。 选择要分配给用户（组）的角色。

















































