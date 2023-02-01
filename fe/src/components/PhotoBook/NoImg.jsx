const NoImg = () => {
  return(
    <div
      style={{
        margin:'10px',
        transform:'translate(0,200%)'
      }}>
      <span
        style={{
          fontWeight:'bold',
          color:'#868686',
          
        }}>
          일치하는 검색어가 없습니다.
      </span>
    </div>
  )
}

export default NoImg