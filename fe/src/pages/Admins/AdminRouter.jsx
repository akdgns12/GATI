import React from 'react'
import { Routes, Route } from 'react-router'

import AdminPage from './AdminPage'
import AdminCreate from './AdminCreate'
import AdminEdit from './AdminEdit'


export default function AdminRouter(props) {
  return (
    <Routes>
      <Route path='/' element={<AdminPage/>}/>
      <Route path='/edit' element={<AdminEdit/>}/>
      <Route path='/create' element={<AdminCreate/>}/>
    </Routes>
  )
}
