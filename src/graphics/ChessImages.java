package graphics;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import definitions.PieceType;
import definitions.SpaceColor;
import definitions.PieceColor;

public class ChessImages {
	private static BufferedImage whitePawnWhite, whitePawnGrey, blackPawnWhite, blackPawnGrey,
						 whiteKnightWhite, whiteKnightGrey, blackKnightWhite, blackKnightGrey,
						 whiteBishopWhite, whiteBishopGrey, blackBishopWhite, blackBishopGrey,
						 whiteRookWhite, whiteRookGrey, blackRookWhite, blackRookGrey,
						 whiteQueenWhite, whiteQueenGrey, blackQueenWhite, blackQueenGrey,
						 whiteKingWhite, whiteKingGrey, blackKingWhite, blackKingGrey;
	static {
		try {
		    whitePawnWhite = ImageIO.read(new File("C:\\Users\\robby35us\\workspace\\KMChess\\images\\WhitePawnWhite.jpg"));
		    whitePawnGrey = ImageIO.read(new File("C:\\Users\\robby35us\\workspace\\KMChess\\images\\WhitePawnGrey.jpg"));
		    blackPawnWhite = ImageIO.read(new File("C:\\Users\\robby35us\\workspace\\KMChess\\images\\BlackPawnWhite.jpg")); 
		    blackPawnGrey = ImageIO.read(new File("C:\\Users\\robby35us\\workspace\\KMChess\\images\\BlackPawnGrey.jpg"));
			whiteKnightWhite = ImageIO.read(new File("C:\\Users\\robby35us\\workspace\\KMChess\\images\\WhiteKnightWhite.jpg")); 
			whiteKnightGrey = ImageIO.read(new File("C:\\Users\\robby35us\\workspace\\KMChess\\images\\WhiteKnightGrey.jpg")); 
			blackKnightWhite = ImageIO.read(new File("C:\\Users\\robby35us\\workspace\\KMChess\\images\\BlackKnightWhite.jpg"));
			blackKnightGrey = ImageIO.read(new File("C:\\Users\\robby35us\\workspace\\KMChess\\images\\BlackKnightGrey.jpg"));
			whiteBishopWhite = ImageIO.read(new File("C:\\Users\\robby35us\\workspace\\KMChess\\images\\WhiteBishopWhite.jpg")); 
			whiteBishopGrey = ImageIO.read(new File("C:\\Users\\robby35us\\workspace\\KMChess\\images\\WhiteBishopGrey.jpg")); 
			blackBishopWhite = ImageIO.read(new File("C:\\Users\\robby35us\\workspace\\KMChess\\images\\BlackBishopWhite.jpg"));
			blackBishopGrey = ImageIO.read(new File("C:\\Users\\robby35us\\workspace\\KMChess\\images\\BlackBishopGrey.jpg"));
			whiteRookWhite= ImageIO.read(new File("C:\\Users\\robby35us\\workspace\\KMChess\\images\\WhiteRookWhite.jpg")); 
			whiteRookGrey = ImageIO.read(new File("C:\\Users\\robby35us\\workspace\\KMChess\\images\\WhiteRookGrey.jpg"));
			blackRookWhite = ImageIO.read(new File("C:\\Users\\robby35us\\workspace\\KMChess\\images\\BlackRookWhite.jpg"));
			blackRookGrey = ImageIO.read(new File("C:\\Users\\robby35us\\workspace\\KMChess\\images\\BlackRookGrey.jpg"));
			whiteQueenWhite = ImageIO.read(new File("C:\\Users\\robby35us\\workspace\\KMChess\\images\\WhiteQueenWhite.jpg"));
			whiteQueenGrey = ImageIO.read(new File("C:\\Users\\robby35us\\workspace\\KMChess\\images\\WhiteQueenGrey.jpg"));
			blackQueenWhite = ImageIO.read(new File("C:\\Users\\robby35us\\workspace\\KMChess\\images\\BlackQueenWhite.jpg"));
			blackQueenGrey = ImageIO.read(new File("C:\\Users\\robby35us\\workspace\\KMChess\\images\\BlackQueenGrey.jpg"));
			whiteKingWhite = ImageIO.read(new File("C:\\Users\\robby35us\\workspace\\KMChess\\images\\WhiteKingWhite.jpg")); 
			whiteKingGrey = ImageIO.read(new File("C:\\Users\\robby35us\\workspace\\KMChess\\images\\WhiteKingGrey.jpg")); 
			blackKingWhite = ImageIO.read(new File("C:\\Users\\robby35us\\workspace\\KMChess\\images\\BlackKingWhite.jpg")); 
			blackKingGrey = ImageIO.read(new File("C:\\Users\\robby35us\\workspace\\KMChess\\images\\BlackKingGrey.jpg"));
		} catch (IOException e) {
			System.out.println("Images failed to load");
			System.out.println(e.getMessage());
		}
	}
	
	public static Image getImage(PieceType type, PieceColor pc, SpaceColor sc){
		
		switch(type){
			case Pawn: 
				if(pc == PieceColor.White)
					if(sc == SpaceColor.White)
						return whitePawnWhite;
					else
						return whitePawnGrey;
				else
					if(sc == SpaceColor.White)
						return blackPawnWhite;
					else 
						return blackPawnGrey;
			case Rook: 
				if(pc == PieceColor.White)
					if(sc == SpaceColor.White)
						return whiteRookWhite;
					else
						return whiteRookGrey;
				else
					if(sc == SpaceColor.White)
						return blackRookWhite;
					else 
						return blackRookGrey;
			case Bishop: 
				if(pc == PieceColor.White)
					if(sc == SpaceColor.White)
						return whiteBishopWhite;
					else
						return whiteBishopGrey;
				else
					if(sc == SpaceColor.White)
						return blackBishopWhite;
					else 
						return blackBishopGrey;
			case Knight: 
				if(pc == PieceColor.White)
					if(sc == SpaceColor.White)
						return whiteKnightWhite;
					else
						return whiteKnightGrey;
				else
					if(sc == SpaceColor.White)
						return blackKnightWhite;
					else 
						return blackKnightGrey;
			case Queen: 
				if(pc == PieceColor.White)
					if(sc == SpaceColor.White)
						return whiteQueenWhite;
					else
						return whiteQueenGrey;
				else
					if(sc == SpaceColor.White)
						return blackQueenWhite;
					else 
						return blackQueenGrey;
			case King:
				if(pc == PieceColor.White)
					if(sc == SpaceColor.White)
						return whiteKingWhite;
					else
						return whiteKingGrey;
				else
					if(sc == SpaceColor.White)
						return blackKingWhite;
					else 
						return blackKingGrey;
			default:
				return null;
		}
		
	}
}
