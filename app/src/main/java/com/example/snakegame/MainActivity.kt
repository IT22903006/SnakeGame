package com.example.snakegame

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import java.util.*
import kotlin.math.pow
import kotlin.math.sqrt

class MainActivity : Activity() {
    private lateinit var highScoreView: TextView
    private var highScore = 0
    private var scorex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //store and retrieve high score
        val sharedPreferences = getSharedPreferences("SnakeGame", Context.MODE_PRIVATE)
        highScore = sharedPreferences.getInt("HighScore", 0) // Retrieve the high score from SharedPreferences

        val buttonPlay = findViewById<Button>(R.id.btnhome)

        buttonPlay.setOnClickListener{
            val nextPage = Intent(this, HomePage::class.java);
            startActivity(nextPage);
            finish();
        }
        highScoreView = findViewById<TextView>(R.id.high_score) // Make sure to have a TextView for high score in your layout
        highScoreView.text = "High Score: $highScore"

        val board = findViewById<RelativeLayout>(R.id.board)
        val border = findViewById<RelativeLayout>(R.id.relativeLayout)
        val lilu = findViewById<LinearLayout>(R.id.lilu)
        val upButton = findViewById<Button>(R.id.up)
        val downButton = findViewById<Button>(R.id.down)
        val leftButton = findViewById<Button>(R.id.left)
        val rightButton = findViewById<Button>(R.id.right)
        val pauseButton = findViewById<Button>(R.id.pause)
        val newgame = findViewById<Button>(R.id.new_game)
        val resume = findViewById<Button>(R.id.resume)
        val playagain = findViewById<Button>(R.id.playagain)
        val score = findViewById<Button>(R.id.score)
        val score2 = findViewById<Button>(R.id.score2)
        val meat = ImageView(this)
        val snake = ImageView(this)
        val snakeSegments = mutableListOf(snake)
        val handler = Handler()
        var delayMillis = 30L
        var currentDirection = "right"
        scorex = 0

        board.visibility = View.INVISIBLE
        playagain.visibility = View.INVISIBLE
        score.visibility = View.INVISIBLE
        score2.visibility = View.INVISIBLE

        //new game
        newgame.setOnClickListener {
            board.visibility = View.VISIBLE
            newgame.visibility = View.INVISIBLE
            resume.visibility = View.INVISIBLE
            score2.visibility = View.VISIBLE

            snake.setImageResource(R.drawable.snake)
            snake.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            board.addView(snake)
            snakeSegments.add(snake)

            //store the initial x and y positions of the snake
            var snakeX = snake.x
            var snakeY = snake.y

            meat.setImageResource(R.drawable.meat)
            meat.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            board.addView(meat)

            val random = Random()
            var randomX = random.nextInt(801) - 400
            var randomY = random.nextInt(801) - 400

            meat.x = randomX.toFloat()
            meat.y = randomY.toFloat()

            //calculates the Euclidean distance between the snake and the meat
            fun checkFoodCollision() {
                val distanceThreshold = 50
                val distance = sqrt((snake.x - meat.x).pow(2) + (snake.y - meat.y).pow(2))

                if (distance < distanceThreshold) {
                    val newSnake = ImageView(this)
                    newSnake.setImageResource(R.drawable.snake)
                    newSnake.layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    board.addView(newSnake)

                    snakeSegments.add(newSnake)

                    randomX = random.nextInt(801) - 100
                    randomY = random.nextInt(801) - 100

                    meat.x = randomX.toFloat()
                    meat.y = randomY.toFloat()

                    delayMillis--
                    scorex++
                    score2.text = "score : $scorex"

                    //update highs core
                    if (scorex > highScore) {
                        highScore = scorex
                        highScoreView.text = "High Score: $highScore"
                        with(sharedPreferences.edit()) {
                            putInt("HighScore", highScore)
                            apply()
                        }
                    }
                }
            }

            val runnable = object : Runnable {
                override fun run() {
                    for (i in snakeSegments.size - 1 downTo 1) {
                        snakeSegments[i].x = snakeSegments[i - 1].x
                        snakeSegments[i].y = snakeSegments[i - 1].y
                    }

                    when (currentDirection) {
                        "up" -> {
                            snakeY -= 10
                            if (snakeY < -490) {
                                snakeY = -490f
                                border.setBackgroundColor(getResources().getColor(R.color.red))
                                playagain.visibility = View.VISIBLE
                                currentDirection = "pause"
                                lilu.visibility = View.INVISIBLE
                                score.text = "your score is $scorex"
                                score.visibility = View.VISIBLE
                                score2.visibility = View.INVISIBLE
                            }
                            snake.translationY = snakeY
                        }
                        "down" -> {
                            snakeY += 10
                            val maxY = board.height / 2 - snake.height + 30
                            if (snakeY > maxY) {
                                snakeY = maxY.toFloat()
                                border.setBackgroundColor(getResources().getColor(R.color.red))
                                playagain.visibility = View.VISIBLE
                                currentDirection = "pause"
                                lilu.visibility = View.INVISIBLE
                                score.text = "your score is $scorex"
                                score.visibility = View.VISIBLE
                                score2.visibility = View.INVISIBLE
                            }
                            snake.translationY = snakeY
                        }
                        "left" -> {
                            snakeX -= 10
                            if (snakeX < -490) {
                                snakeX = -490f
                                border.setBackgroundColor(getResources().getColor(R.color.red))
                                playagain.visibility = View.VISIBLE
                                currentDirection = "pause"
                                lilu.visibility = View.INVISIBLE
                                score.text = "your score is $scorex"
                                score.visibility = View.VISIBLE
                                score2.visibility = View.INVISIBLE
                            }
                            snake.translationX = snakeX
                        }
                        "right" -> {
                            snakeX += 10
                            val maxX = board.width / 2 - snake.width + 30
                            if (snakeX > maxX) {
                                snakeX = maxX.toFloat()
                                border.setBackgroundColor(getResources().getColor(R.color.red))
                                playagain.visibility = View.VISIBLE
                                currentDirection = "pause"
                                lilu.visibility = View.INVISIBLE
                                score.text = "your score is $scorex"
                                score.visibility = View.VISIBLE
                                score2.visibility = View.INVISIBLE
                            }
                            snake.translationX = snakeX
                        }
                        "pause" -> {
                            // Do nothing if paused
                        }
                    }
                    checkFoodCollision()
                    handler.postDelayed(this, delayMillis)
                }
            }

            handler.postDelayed(runnable, delayMillis)

            upButton.setOnClickListener { currentDirection = "up" }
            downButton.setOnClickListener { currentDirection = "down" }
            leftButton.setOnClickListener { currentDirection = "left" }
            rightButton.setOnClickListener { currentDirection = "right" }
            pauseButton.setOnClickListener {
                currentDirection = "pause"
                board.visibility = View.INVISIBLE
                newgame.visibility = View.VISIBLE
                resume.visibility = View.VISIBLE
            }
            resume.setOnClickListener {
                currentDirection = "right"
                board.visibility = View.VISIBLE
                newgame.visibility = View.INVISIBLE
                resume.visibility = View.INVISIBLE
            }
            playagain.setOnClickListener { recreate() }
        }
    }
}
