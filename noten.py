#!/usr/bin/env python
# coding: utf-8



import pandas as pd
import numpy as np
import seaborn as sns
import matplotlib.pyplot as plt
target_lp = 90
data = pd.read_csv('./noten.csv')

#data.head(5)
#data.info()

data.sort_values(by='note', inplace=True)

acc_note = 0.0
acc_lp = 0
# the use of iterrows and all iteration over dataframes is discouraged for performance reasons
for index, row in data.iterrows():
    if acc_lp + row['lp'] < target_lp:
        acc_lp = acc_lp + row['lp']
        acc_note = acc_note + row['lp'] * row['note']
    else:
        weight = target_lp - acc_lp
        acc_note = acc_note + weight * row['note']
        break
acc_note = acc_note / target_lp
print(acc_note)

