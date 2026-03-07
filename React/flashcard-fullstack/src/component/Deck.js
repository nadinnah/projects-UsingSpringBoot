import React from 'react';

const Deck = ({flashcardLists}) => {
    return (
        <div className="card-grid">
            {flashcardLists.map(flashcardList=>{
                return <Flashcard flashcardList={flashcardList} key={flashcardList.id}/>
            })}
        </div>
    );
}

export default Deck;
