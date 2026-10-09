package com.zetcode;

/*
 * [ABSTRACT FACTORY] Role: ABSTRACT FACTORY (the interface)
 *
 * Declares one "create" method for each PRODUCT in a family:
 * a paddle, a ball and a brick.
 *
 * Each concrete factory (ClassicFactory, HardFactory) produces one FAMILY of
 * objects that are designed to be used together. Whoever holds a GameFactory
 * just calls createBall() etc. and never learns which concrete class it got.
 *
 * Notice the return types are the BASE classes (Paddle, Ball, Brick).
 * That is what keeps the rest of the game independent of FastBall,
 * StrongBrick and friends.
 */
public interface GameFactory {

    Paddle createPaddle();

    Ball createBall();

    Brick createBrick(int x, int y);
}
