import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import Card from './Card';
import AddDeck from './cards/AddDeck'
import OverlayModal from './component/OverlayModal';
import AddCard from './cards/AddCard';

const router = createBrowserRouter([
  {path:"/",element:<App/>},
  {path:"/:deckId", element:<Card/>},
  {path:"/add_deck", element:<AddDeck/>},
  {path:"/:deckId/add_card", element:<AddCard/>},
  {path:"/overlay", element:<OverlayModal/>}
]);

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <RouterProvider router={router}/>
  </React.StrictMode>
);

