import "../node_modules/bootstrap/dist/css/bootstrap.min.css"
import FlashcardList from "./component/FlashcardList";
import "./Card.css"
import React, {useState, useEffect} from "react";
import axios from "axios";
import { Link } from 'react-router-dom';


const Card = () => {
    const [flashcards, setFlashcards]= useState([])

    useEffect(()=>{
        loadFlashcards()
    },[])

    const loadFlashcards= async()=>{
    const result=await axios.get("http://localhost:8080/card")
    setFlashcards(result.data)
    }

    return (
        <div>
            <Link to={`/`}>
            <button className="btn btn-light">Go back</button> 
            </Link>
            <FlashcardList flashcards={flashcards}/>
      
        </div>
    );
}

// const SAMPLE_FLASHCARDS=[
//   {
//     id:1,
//     frontText: "Hallo",
//     backText:"Hello"
//   },
//   {
//     id:2,
//     frontText: "Frau",
//     backText:"Woman"
//   }
// ]

export default Card;
