import "../node_modules/bootstrap/dist/css/bootstrap.min.css"
import FlashcardList from "./component/FlashcardList";
import "./App.css"
import Navbar from './layout/Navbar';
import React, {useState} from "react";
function App() {

  const[flashcards, setFlashcards]= useState(SAMPLE_FLASHCARDS)

  return (
    <div className="App">
      <Navbar/>
      <FlashcardList flashcards={flashcards}/>

    </div>
  );
}

const SAMPLE_FLASHCARDS=[
  {
    id:1,
    frontText: "Hallo",
    backText:"Hello"
  },
  {
    id:2,
    frontText: "Frau",
    backText:"Woman"
  }
]

export default App;
