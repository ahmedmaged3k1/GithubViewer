package com.example.githubviewer.bishop

class Chessboard {
    private val pieces: MutableMap<String, ChessPiece> = mutableMapOf()

    fun addPiece(piece: ChessPiece) {
        pieces[piece.position] = piece
    }

    fun isPositionOccupied(position: String): Boolean {
        return pieces.containsKey(position)
    }

    fun getPieceAtPosition(position: String): ChessPiece? {
        return pieces[position]
    }
}

open class ChessPiece(
    val color: String,
    var position: String,
    var alive: Boolean = true
) {
    open fun isValidMove(toPosition: String, chessboard: Chessboard): Boolean {
        return true // Override in specific chess piece classes
    }

    open fun move(toPosition: String, chessboard: Chessboard) {
        require(isValidMove(toPosition, chessboard)) {
            "Invalid move: $toPosition is not a valid move for this chess piece."
        }

        if (chessboard.isPositionOccupied(toPosition)) {
            val pieceAtTarget = chessboard.getPieceAtPosition(toPosition)
            pieceAtTarget?.capture()
        }

        position = toPosition
        println("${this.javaClass.simpleName} moved to $toPosition")
    }

    open fun capture() {
        if (alive) {
            alive = false
            println("${this.javaClass.simpleName} captured")
        } else {
            println("${this.javaClass.simpleName} is already captured")
        }
    }

    open fun revive() {
        if (!alive) {
            alive = true
            println("${this.javaClass.simpleName} revived")
        } else {
            println("${this.javaClass.simpleName} is already alive")
        }
    }

    fun displayDetails() {
        println("${this.javaClass.simpleName} details: Color - $color, Alive - $alive, Position - $position")
    }
}

class Bishop(color: String, position: String) : ChessPiece(color, position) {
    override fun isValidMove(toPosition: String, chessboard: Chessboard): Boolean {
        val fromColumn = position[0].toInt()
        val fromRow = position[1].toString().toInt()

        val toColumn = toPosition[0].toInt()
        val toRow = toPosition[1].toString().toInt()

        // Check if the move is diagonal
        val columnDifference = Math.abs(toColumn - fromColumn)
        val rowDifference = Math.abs(toRow - fromRow)

        if (columnDifference != rowDifference) {
            return false
        }

        // Check if there is a piece of the same color along the diagonal
        val columnStep = if (toColumn > fromColumn) 1 else -1
        val rowStep = if (toRow > fromRow) 1 else -1

        var currentColumn = fromColumn + columnStep
        var currentRow = fromRow + rowStep

        while (currentColumn != toColumn && currentRow != toRow) {
            val currentPosition = "${currentColumn.toChar()}$currentRow"
            if (chessboard.isPositionOccupied(currentPosition)) {
                val pieceAtPosition = chessboard.getPieceAtPosition(currentPosition)
                if (pieceAtPosition?.color == color) {
                    // There is a piece of the same color in the diagonal path
                    return false
                }
            }

            currentColumn += columnStep
            currentRow += rowStep
        }

        return !chessboard.isPositionOccupied(toPosition) ||
                chessboard.getPieceAtPosition(toPosition)?.color != color
    }
}

class Rook(color: String, position: String) : ChessPiece(color, position) {
    override fun isValidMove(toPosition: String, chessboard: Chessboard): Boolean {
        val fromColumn = position[0].toInt()
        val fromRow = position[1].toString().toInt()

        val toColumn = toPosition[0].toInt()
        val toRow = toPosition[1].toString().toInt()

        // Check if the move is either horizontal or vertical
        val isHorizontalMove = fromRow == toRow
        val isVerticalMove = fromColumn == toColumn

        if (!isHorizontalMove && !isVerticalMove) {
            return false
        }

        // Check if there is a piece of the same color along the horizontal or vertical path
        val columnStep = if (toColumn > fromColumn) 1 else if (toColumn < fromColumn) -1 else 0
        val rowStep = if (toRow > fromRow) 1 else if (toRow < fromRow) -1 else 0

        var currentColumn = fromColumn + columnStep
        var currentRow = fromRow + rowStep

        while (currentColumn != toColumn || currentRow != toRow) {
            val currentPosition = "${currentColumn.toChar()}$currentRow"
            if (chessboard.isPositionOccupied(currentPosition)) {
                val pieceAtPosition = chessboard.getPieceAtPosition(currentPosition)
                if (pieceAtPosition?.color == color) {
                    // There is a piece of the same color in the horizontal or vertical path
                    return false
                }
            }

            currentColumn += columnStep
            currentRow += rowStep
        }

        return !chessboard.isPositionOccupied(toPosition) ||
                chessboard.getPieceAtPosition(toPosition)?.color != color
    }
}

fun main() {
    val chessboard = Chessboard()

    val whiteBishop = Bishop(color = "White", position = "C1")
    val blackBishop = Bishop(color = "Black", position = "E3")

    chessboard.addPiece(whiteBishop)
    chessboard.addPiece(blackBishop)

    whiteBishop.displayDetails()
    blackBishop.displayDetails()

    whiteBishop.move("E3", chessboard)
    whiteBishop.displayDetails()
    blackBishop.displayDetails()
}

