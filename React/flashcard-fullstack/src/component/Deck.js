import React, {useState} from 'react';

const Deck = ({deck}) => {
    //const [flip, setFlip]= useState(false)
    return (
        <div className='stack'>
            <div className="deck">
            </div>
            <div className="deck2 ">
            </div>
            <div className="deck3">
            {deck.deckName}
            </div>
        </div>
    );
}

export default Deck;
