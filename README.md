# PIDPro
A free and open-source PID controller demo.

(README images are from v1.0)

## Overview
PIDPro is a simple java application designed for learning how to use and tune a PID controller. 
While this isn't designed to exactly simulate real-life behavior, it does implement many corollaries.
The program, along with the code itself, uses phrases akin to a moving object, such as mass, friction, etc.
However, it can still effectively simulate other systems that implement PID control.

## How to use
In the main window of the program, there are several parameters that you can control for when the simulation runs:
![image](https://github.com/user-attachments/assets/2757f490-2e29-4493-aa06-57c1e36321dd)

By clicking the "Parameter Explanations" button, a window with a simple explanation for each parameter is displayed.

Setting and resetting the parameter values is pretty self-explanatory.

You can run a simulation with the given parameters by pressing the "Run PID Simulation" button. A graph of the
resulting data will automatically be displayed (JFreeChart library). The red line represents the controlled object's 
position, the blue line represents the PID's output (or really applied force to the object), and the black line 
represents the setpoint.

## Examples
Not very well-tuned:
![image](https://github.com/user-attachments/assets/b6d915d0-3aa5-4f62-91bf-d8d8b45f603a)

Well-tuned:
![image](https://github.com/user-attachments/assets/b2284e01-e21b-47e1-842b-e41bec087a4c)
