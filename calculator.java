package com.example.demo.demo2;

/**
 * 描述:
 *简单用栈来实现 ，并且用double双精度浮点运算实现，bigdecimal也行，
 *
 * @Author liutao
 * @create 2023-04-17 11:32
 */
import java.util.Stack;

public class Calculator {
    //当前值
    private double currentValue;
    //撤销的运算栈 保存上次运算的操作结果
    private Stack<Double> undoStack;
    //重做的运算栈，保存撤销后重新执行的结果
    private Stack<Double> redoStack;

    public Calculator() {
        //初始计算器当前为0
        currentValue = 0;
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }
    //每次运算时候把当前计算器值保存进去方便拿到历史记录

    private void saveState() {
        undoStack.push(currentValue);
        redoStack.clear();
    }

    public void add(double value) {
        saveState();
        currentValue += value;
    }

    public void subtract(double value) {
        saveState();
        currentValue -= value;
    }

    public void multiply(double value) {
        saveState();
        currentValue *= value;
    }

    public void divide(double value) {
        if (value != 0) {
            saveState();
            currentValue /= value;
        } else {
            throw new RuntimeException("不能除0");
        }
    }

    //回滚时候需要弹出最近的一次值，并且为了重做需要把当前值放进去
    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(currentValue);
            currentValue = undoStack.pop();
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(currentValue);
            currentValue = redoStack.pop();
        }
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        calculator.add(5);
        System.out.println("运算+ 5: " + calculator.getCurrentValue());
        calculator.subtract(2);
        System.out.println("运算- 2: " + calculator.getCurrentValue());
        calculator.multiply(3);
        System.out.println("运算* 3: " + calculator.getCurrentValue());
        calculator.divide(4);
        System.out.println("运算/ 4: " + calculator.getCurrentValue());

        calculator.undo();
        System.out.println("撤销运算: " + calculator.getCurrentValue());

        calculator.redo();
        System.out.println("重做运算: " + calculator.getCurrentValue());
    }
}

