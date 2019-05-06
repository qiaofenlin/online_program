#!/usr/bin/env python
# encoding: utf-8
'''
@author: qfl
@file: sort.py
@time: 19-3-7 下午2:57
@desc:
'''

# # 冒泡排序
# def bubblesort(list):
#     if len(list) <=1:
#         return
#     for i in range(len(list)-1):
#         flag = False
#         for j in range(len(list)-i-1):
#             if list[j] > list[j+1]:
#                 list[j],list[j+1] = list[j+1],list[j]
#                 flag = True
#         if flag is False:
#             break
#     return list
#
# # 插入排序
#
# def insertSort(arr):
#     for i in range(1, len(arr)):
#         j = i - 1
#         key = arr[i]
#         while j >= 0:
#             if arr[j] > key:
#                 arr[j + 1] = arr[j]
#                 arr[j] = key
#             j -= 1
#     return arr
#
#
# nums = [5,2,45,6,8,2,1]

# print(bubblesort(nums))
# print(insertSort(nums))


def QuickSort(arr, firstIndex, lastIndex):
    if firstIndex < lastIndex:
        divIndex = Partition(arr, firstIndex, lastIndex)
        QuickSort(arr, firstIndex, divIndex)
        QuickSort(arr, divIndex + 1, lastIndex)
    else:
        return

def Partition(arr, firstIndex, lastIndex):
    i = firstIndex - 1
    for j in range(firstIndex, lastIndex):
        if arr[j] <= arr[lastIndex]:
            i = i + 1
            arr[i], arr[j] = arr[j], arr[i]
    arr[i + 1], arr[lastIndex] = arr[lastIndex], arr[i + 1]
    return i

def Sort_Partition(arr,firstIndex, lastIndex):
    i = firstIndex - 1
    for j in range(firstIndex,lastIndex):
        if arr[j] <= arr[lastIndex]:
            i = i + 1
            arr[i], arr[j] = arr[j],arr[i]
    arr[i+1],arr[lastIndex] = arr[lastIndex],arr[i+1]
    return i

# def quickSort(arr,firstIndex,lastIndex):
#     if firstIndex < lastIndex:
#         divIndex = Sort_Partition(arr,firstIndex,lastIndex)
#         quickSort(arr,firstIndex,divIndex)
#         quickSort(arr,divIndex+1,lastIndex)
#     else:
#         # 终止条件
#         return


# print("initial array:\n", arr)
# QuickSort(arr, 0, len(arr) - 1)
# print("result array:\n", arr)

# class Solution(object):
#     def twoSum(self, nums, target):
#         """
#         :type nums: List[int]
#         :type target: int
#         :rtype: List[int]
#         """
#         result = []
#         print(nums)
#         for i, each in enumerate(nums):
#             if abs(target-each) >=0 and i not in result:
#                 try:
#                     tmp = nums.index(target - each)
#                     if tmp != i:
#                         result.append(i)
#                         result.append(tmp)
#                 except:
#                     continue
#         return result
class Solution(object):
    def twoSum(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        if len(nums) <= 1:
            return False
        buff_dict,list,list_all = {},[],[]
        for i in range(len(nums)):
            if nums[i] in buff_dict:
                print(buff_dict)
                list.append(buff_dict[nums[i]])
                list.append(i)
                list_all.append(list)
                list = []
            else:
                buff_dict[target-nums[i]] = i
        return list_all

nums = [5,20,1,8,2, 7, 11, 15]
target = 9
a=Solution()
b = a.twoSum(nums,target)
print(b)


# def quick(arr,first,last):
#     if first < last:
#         divIndex = P(arr,first,last)
#         quick(arr,first,divIndex)
#         quick(arr,divIndex + 1,last)
#     else:
#         return
#
# def P(arr,first,last):
#     i = first - 1
#     for j in range(first,last):
#         if arr[j]<=arr[last]:
#             i = i + 1
#             arr[i],arr[j] = arr[j],arr[i]
#     arr[last],arr[i+1] = arr[i+1],arr[last]
#     return i

# if __name__ == '__main__':
#     arr = [1, 4, 7, 1, 5, 5, 3, 85, 34, 75, 23, 75, 2, 0]
#
#     print("initial array:\n", arr)
#     quick(arr, 0, len(arr) - 1)
#     print("result array:\n", arr)