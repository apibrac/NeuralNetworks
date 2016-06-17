# NeuralNetworks

Basic computation of Artificial Neural Networks and Convolutional Neural Networks.

Completely modulable to build any architecture: each structure that gather several substructures (in parallel or in series) can take any list of substructures.

Can easily build a complete network from the very atomic elements.

Use the backpropagation the calibrate the parameters. The alpha parameter of the backpropagation can be chosen and changed at each sampling. A recording/absorption system allow the network to store the effect of several learning data without changing its parameters, and absorb these changing all at once.

# Implementation

## Perceptron

Any neural network that take in input a vector of size I and give in output a vector of size O.

Can be of three different types:

### Neurone

A simple neurone of size I. Only one value in output.

Build from: - its size I (int)

### Couche

A layer of any perceptrons (put in parallel). They can be simple neurones for classic layers, or any type of perceptron (even multi-layers one). They need to have the same entrance size I. The total output size O is the sum of them.

Build from: - a list of perceptrons

### Reseau

A network or perceptrons (put in series). They can be simple layers for classic networks, or any type of perceptron (even all different). Each one need to have the same entrance size than the output size of the previous. The global entrance and outputs sizes are respectively the one of the first element and of the last one.

Build from - a list of perceptrons

## ComplexNN

Any neural network (this class is similar than perceptron) that take in input and output matrices of 3 dimensions (sizes are fix for one particular ComplexNN).

Can be of four different types:

### Adaptation

This class hide a perceptron inside but has the shape of a ComplexNN: use three dimentional matrices but execute a unidimensional perceptron inside (of good size).

Build from - a perceptron

### Sequence

Just like Reseau, this class connects (in series) a list of ComplexNN when their sides are coherent.

Build from - a list of ComplexNN

### Convolution

Convolutional layer. Run throught the entrance matrix (with its scanning window) and appy the same perceptron on all extracted submatrices. The perceptron can be of any form, a simple neural layer or a complex network.

Build from - chosen sizes of window
    - a perceptron 
    

### Subsampling

Layer that average values by square of four: divide the size if the matrix by 2 on two directions.

Note: this layer doesn't have fix sizes of entrance and output (output sizes depends on the output sizes of the previous layer).

# Classic construction

Usually a CNN alternates several time a layer of convolution and a layer of subsampling, then applies a classic ANN (several different layers of several neurones) and finishes by a layer with same output size than the number of output values wanted.
