import "../node_modules/bootstrap/dist/css/bootstrap.min.css"
import DeckList from "./component/DeckList";
import "./App.css"
import React, {useState, useEffect}from "react";
import axios from "axios";


function App() {

  // const [flashcards, setFlashcards]= useState([])
  const [decks, setDecks]= useState([])
  // const [newCard,setNewCard]= useState()

  useEffect(()=>{
    loadDecks()
  },[])

  // useEffect(()=>{
  //   loadFlashcards()
  // },[])

  const loadDecks= async()=>{
    const result=await axios.get("http://localhost:8080/deck")
    setDecks(result.data)

  }

  // const loadFlashcards= async()=>{
  //   const result=await axios.get("http://localhost:8080/card")
  //   setFlashcards(result.data)

  // }

  // const addFlashcard=(frontText, backText)=>{
  //   const result= axios.post
  // }

  return (
    <div className="App">
      <DeckList decks={decks} />
      {/* <FlashcardList flashcards={flashcards}/> */}
      {/* <button className="btn btn-outline-success" onClick={addFlashcard} >Add New Deck</button>
       */}
    </div>
  );
}

// const SAMPLE_DECKS=[
//   {
//     id:1,
//     deckName:"german",
//   },
  
// ]

// const SAMPLE_FLASHCARDLISTS=[
//   {
//     id:1,
//     card_list_category:"ger",
//     deck_id:1
//   },
//   {
//     id:2,
//     card_list_category:"ger",
//     deck_id:2
//   },
  
// ]


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
