import matplotlib.pyplot as plt
import numpy as np

# 设置周数从0到50
weeks = np.linspace(0, 49, 50)

# 生成更曲折的累计成本线
np.random.seed(0)  # 为了使结果可重复
# 增大增量的波动范围，使曲线更曲折
increments = np.random.randint(1000, 15000, size=49)
costs = np.cumsum(increments)
costs = np.insert(costs, 0, 0)  # 在第0周插入成本0
# 缩放成本使其在第50周达到约170,000
costs = costs * (170000 / costs[-1])

# 绘制成本基准线
plt.figure(figsize=(10, 6))
plt.plot(weeks, costs, color='black')

# 绘制y=200,000的总预算可用线
plt.axhline(y=200000, color='red', linestyle='--', label='Total Budget Available')

# 绘制y=170,000的屋顶线（成本基准线的最高点）
plt.axhline(y=170000, color='blue', linestyle='--', label='Cost Baseline Peak')

# 在y=170,000和y=200,000之间添加“Management Reserve”标签
plt.text(25, 185000, 'Management Reserve', ha='center', va='center')

# 设置标题和坐标轴标签
plt.title('Cost Baseline Graph with More Zigzag Pattern')
plt.xlabel('Weeks')
plt.ylabel('Cumulative Cost (£)')

# 设置y轴范围
plt.ylim(0, 220000)

# 添加图例
plt.legend()

# 显示网格
plt.grid(True)

# 显示图形
plt.show()
