import "../node_modules/bootstrap/dist/css/bootstrap.min.css"
import FlashcardList from "./component/FlashcardList";
import "./App.css"
import React, {useState, useEffect}from "react";
import axios from "axios";
function App() {

  const [flashcards, setFlashcards]= useState([])
  const [newCard,setNewCard]= useState()


  useEffect(()=>{
    loadFlashcards()
  },[])

  const loadFlashcards= async()=>{
    const result=await axios.get("http://localhost:8080/card")
    setFlashcards(result.data)

  }

  const addFlashcard=(frontText, backText)=>{
    const result= axios.post
  }

  return (
    <div className="App">
      <DeckList decks={FlashcardList}/>
      <FlashcardList flashcards={flashcards}/>
      <button className="btn btn-outline-success" onClick={addFlashcard} >Add New Deck</button>
      
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

export default App;
